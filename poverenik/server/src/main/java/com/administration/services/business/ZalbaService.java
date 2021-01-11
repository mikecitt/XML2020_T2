package com.administration.services.business;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;
import com.administration.services.model.Zalbecutanje;
import com.administration.services.model.Zalbenaodluku;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Service
public class ZalbaService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("zalbacutanje.xml")
    private String zalbaCutanjeId;

    @Value("zalbanaodluku.xml")
    private String zalbaOdlukuId;

    public Zalbecutanje getAllZalbeCutanje() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Zalbecutanje zalbecutanje = null;

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + zalbaCutanjeId);
            res = (XMLResource) col.createResource(zalbaCutanjeId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                zalbecutanje = (Zalbecutanje) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zalbecutanje = new Zalbecutanje();
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

        return zalbecutanje;
    }

    public void addNewZalbaCutanje(Zalbacutanje zalbacutanje) throws Exception {
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId, 0);

            System.out.println("[INFO] Inserting the document: " + zalbaCutanjeId);
            res = (XMLResource) col.createResource(zalbaCutanjeId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zalbecutanje zalbecutanje = null;
            try {
                zalbecutanje = (Zalbecutanje) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zalbecutanje = new Zalbecutanje();
            }
            zalbecutanje.getZalbacutanje().add(zalbacutanje);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(zalbecutanje, os);

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

    public Zalbenaodluku getAllZalbeOdluka() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Zalbenaodluku zalbenaodluku = null;

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + zalbaCutanjeId);
            res = (XMLResource) col.createResource(zalbaOdlukuId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                zalbenaodluku = (Zalbenaodluku) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zalbenaodluku = new Zalbenaodluku();
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

        return zalbenaodluku;
    }

    public void addNewZalbaOdluka(Zalbanaodluku zalbanaodluku) throws Exception {
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId, 0);

            System.out.println("[INFO] Inserting the document: " + zalbaCutanjeId);
            res = (XMLResource) col.createResource(zalbaOdlukuId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zalbenaodluku zalbenaodluku = null;
            try {
                zalbenaodluku = (Zalbenaodluku) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zalbenaodluku = new Zalbenaodluku();
            }
            zalbenaodluku.getZalbanaodluku().add(zalbanaodluku);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(zalbenaodluku, os);

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
