package com.administration.services.business;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.model.Resenja;
import com.administration.services.model.Resenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import org.exist.xmldb.EXistResource;

@Service
public class ResenjeService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("resenje.xml")
    private String resenjeId;

    public Resenja getAllResenja() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Resenja resenja = null;

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + resenjeId);
            res = (XMLResource) col.createResource(resenjeId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                resenja = (Resenja) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                resenja = new Resenja();
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

        return resenja;
    }

    public void addNewResenje(Resenje resenje) throws Exception {
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId, 0);

            System.out.println("[INFO] Inserting the document: " + resenjeId);
            res = (XMLResource) col.createResource(resenjeId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Resenja resenja = null;
            try {
                resenja = (Resenja) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                resenja = new Resenja();
            }
            resenja.getResenje().add(resenje);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(resenja, os);

            res.setContent(os);
            System.out.println("[INFO] Storing the document: " + res.getId());

            col.storeResource(res);
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
}
