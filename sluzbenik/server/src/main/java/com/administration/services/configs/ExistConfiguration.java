package com.administration.services.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

@Configuration
public class ExistConfiguration {

    @Value("${exist.datasource.user:admin}")
    public String user;

    @Value("${exist.datasource.password:}")
    public String password;

    @Value("${exist.driver:org.exist.xmldb.DatabaseImpl}")
    public String driver;

    @Value("xmldb:exist://${exist.datasource.server-name:localhost}:${exist.datasource.port:8089}/exist/xmlrpc")
    public String uri;

    @Bean
    public void setUpDatabase() throws Exception {
        System.out.println("[INFO] Loading driver class: " + driver);
        Class<?> cl = Class.forName(driver);

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
    }

    public Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    public Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(uri + collectionUri, user, password);

        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(uri + path, user, password);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(uri + parentPath, user, password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol
                            .getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    public void prepareForWriting(Marshaller marshaller, OutputStream os, Object entity) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        marshaller.marshal(entity, document);

        document.createProcessingInstruction("xml", "version=\"1.0\" encoding=\"UTF-8\"");
        document.getDocumentElement().setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema#");
        document.getDocumentElement().setAttribute("xmlns", "http://www.w3.org/ns/rdfa#");
        document.getDocumentElement().setAttribute("xmlns:pred", "http://localhost:8080/rdf/predicate/");

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(os);
        t.transform(source, result);
    }
}
