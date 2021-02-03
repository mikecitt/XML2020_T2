package com.administration.services.business;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.model.Obavestenja;
import com.administration.services.model.Obavestenje;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

@Service
public class ObavestenjeService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Value("/db/sluzbenik")
    private String collectionId;

    @Value("obavestenje.xml")
    private String obavestenjeId;

    public Obavestenja getAllObavestenja() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Obavestenja obavestenja = null;

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + obavestenjeId);
            res = (XMLResource) col.createResource(obavestenjeId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                obavestenja = (Obavestenja) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                obavestenja = new Obavestenja();
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

        return obavestenja;
    }

    public void addNewObavestenje(String zahtevId, Obavestenje obavestenje) throws Exception {
        Collection col = null;
        XMLResource res = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId, 0);

            System.out.println("[INFO] Inserting the document: " + obavestenjeId);
            res = (XMLResource) col.createResource(obavestenjeId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Obavestenja obavestenja = null;
            try {
                obavestenja = (Obavestenja) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                obavestenja = new Obavestenja();
            }
            obavestenje.setAbout("http://localhost:8080/obavestenje/" + UUID.randomUUID().toString().replace("-", ""));
            obavestenje.getTelo().getTrazenaInformacija().setRel("pred:refTo");
            obavestenje.getTelo().getTrazenaInformacija().setHref("http://localhost:8080/zahtevcir/" + zahtevId);
            obavestenja.getObavestenje().add(obavestenje);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, obavestenja);

            res.setContent(os);
            System.out.println("[INFO] Storing the document: " + res.getId());
            col.storeResource(res);
            jenaConfiguration.updateRDF(new String(os.toByteArray(), StandardCharsets.UTF_8));
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
