package com.administration.services.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.helpers.MetadataExtractor;
import com.administration.services.helpers.SparqlUtil;
import com.administration.services.model.Korisnik;
import com.administration.services.model.Zahtev;
import com.administration.services.model.Zahtevi;
import com.administration.services.model.Zahtev.Status;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Service
public class ZahtevService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Autowired
    private KorisnikService korisnikService;

    @Value("/db/sluzbenik")
    private String collectionId;

    @Value("zahtev.xml")
    private String zahtevId;

    public Zahtevi getAllZahtevi() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Zahtevi zahtevi = null;

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + zahtevId);
            res = (XMLResource) col.createResource(zahtevId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                zahtevi = (Zahtevi) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zahtevi = new Zahtevi();
            }
        } finally {
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return zahtevi;
    }

    public void addNewZahtev(Zahtev zahtev) throws Exception {

        Collection col = null;
        XMLResource res = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId, 0);

            System.out.println("[INFO] Inserting the document: " + zahtevId);
            res = (XMLResource) col.createResource(zahtevId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zahtevi zahtevi = null;
            try {
                zahtevi = (Zahtevi) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zahtevi = new Zahtevi();
            }
            zahtev.setVocab("http://localhost:8080/rdf/predicate/");
            zahtev.setAbout("http://localhost:8080/zahtevcir/zah_" + UUID.randomUUID().toString().replace("-", ""));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            zahtev.setRel("pred:createdBy");
            zahtev.setHref(k.getAbout());
            zahtev.setStatus(new Status());
            zahtev.getStatus().setValue("OBRADA");
            zahtev.getStatus().setProperty("pred:status_zahteva");
            zahtev.getStatus().setDatatype("xs:string");

            zahtev.getOrgan().getSediste().setProperty("pred:sediste_organa");
            zahtev.getOrgan().getSediste().setDatatype("xs:string");

            zahtev.getDetaljiPredaje().getMesto().setProperty("pred:mesto_predaje");
            zahtev.getDetaljiPredaje().getMesto().setDatatype("xs:string");
            zahtev.getDetaljiPredaje().getDatum().setProperty("pred:datum_predaje");
            zahtev.getDetaljiPredaje().getDatum().setDatatype("xs:date");

            zahtevi.getZahtev().add(zahtev);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, zahtevi);

            res.setContent(os);
            System.out.println("[INFO] Storing the document: " + res.getId());
            col.storeResource(res);
            updateRDF(new String(os.toByteArray(), StandardCharsets.UTF_8));
            System.out.println("[INFO] Done.");

        } finally {
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public void updateRDF(String xml) throws Exception {
        String SPARQL_NAMED_GRAPH_URI = "/administration/metadata";
        // Referencing XML file with RDF data in attributes

        InputStream input = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        // Automatic extraction of RDF triples from XML file
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        System.out.println("[INFO] Extracting metadata from RDFa attributes...");
        metadataExtractor.extractMetadata(input, output);

        String rdf = new String(output.toByteArray(), StandardCharsets.UTF_8);
        // Loading a default model with extracted metadata
        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(rdf.getBytes(StandardCharsets.UTF_8)), SparqlUtil.NTRIPLES);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Extracted metadata as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        // Writing the named graph
        System.out.println("[INFO] Populating named graph \"" + SPARQL_NAMED_GRAPH_URI + "\" with extracted metadata.");
        String sparqlUpdate = SparqlUtil.insertData(jenaConfiguration.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
                new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, jenaConfiguration.updateEndpoint);
        processor.execute();

        // Read the triples from the named graph
        System.out.println();
        System.out.println("[INFO] Retrieving triples from RDF store.");
        System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + "\" named graph.");

        System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + "\".");
        String sparqlQuery = SparqlUtil.selectData(jenaConfiguration.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(jenaConfiguration.queryEndpoint, sparqlQuery);

        // Query the collection, dump output response as XML
        ResultSet results = query.execSelect();

        ResultSetFormatter.out(System.out, results);

        query.close();

        System.out.println("[INFO] End.");
    }

}
