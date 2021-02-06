package com.administration.services.business;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.enums.XslDocumentsPaths;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.helpers.XSLFOTransformer;
import com.administration.services.model.Izvestaj;
import com.administration.services.model.Izvestaji;
import com.administration.services.model.Obavestenje;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

@Service
public class IzvestajService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Autowired
    private XSLFOTransformer transformer;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("izvestaj.xml")
    private String izvestajId;

    public void addIzvestaj(Izvestaj izvestaj) throws Exception {
        Collection col = null;
        XMLResource res = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = existConfiguration.getOrCreateCollection(collectionId, 0);

            System.out.println("[INFO] Inserting the document: " + izvestajId);
            res = (XMLResource) col.createResource(izvestajId, XMLResource.RESOURCE_TYPE);

            System.out.println("[INFO] Unmarshalling XML document to an JAXB instance: ");
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Izvestaji izvestaji = null;
            try {
                izvestaji = (Izvestaji) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                izvestaji = new Izvestaji();
            }
            izvestaji.getIzvestaj().add(izvestaj);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, izvestaji);

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

    public byte[] getIzvestajPDF(Izvestaj izvestaj) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, izvestaj);

        return transformer.generatePDF(XslDocumentsPaths.IZVESTAJ,
                new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public Izvestaj getIzvestaj(String izvestajId) {
        Collection col = null;
        Izvestaj izvestaj = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getOneIzvestaj.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, izvestajId);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    izvestaj = (Izvestaj) unmarshaller.unmarshal(res.getContentAsDOM());
                } finally {
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return izvestaj;
    }

    public Izvestaji getAllIzvestaji() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Izvestaji izvestaji = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId);

            res = (XMLResource) col.createResource(izvestajId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                izvestaji = (Izvestaji) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                izvestaji = new Izvestaji();
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

        return izvestaji;
    }
}
