package com.administration.services.business;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.enums.XslDocumentsPaths;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.helpers.XSLFOTransformer;
import com.administration.services.model.Korisnik;
import com.administration.services.model.Zahtev;
import com.administration.services.model.Zahtev.Status;
import com.administration.services.model.Zahtevi;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

@Service
public class ZahtevService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private XSLFOTransformer transformer;

    @Value("/db/sluzbenik")
    private String collectionId;

    @Value("zahtev.xml")
    private String zahtevId;

    public byte[] getZahtevPDF(Zahtev zahtev) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, zahtev);

        return transformer.generatePDF(XslDocumentsPaths.ZAHTEV, new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public Zahtev getZahtev(String zahtevId) throws Exception {
        Collection col = null;
        Zahtev zahtev = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getOneZahtev.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, zahtevId);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    zahtev = (Zahtev) unmarshaller.unmarshal(res.getContentAsDOM());
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

        return zahtev;
    }

    public Zahtevi getKorisnikZahtevi() {
        Collection col = null;
        Zahtevi zahtevi = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getKorisnikZahtevi.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            String korisnikEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            xqueryExpression = String.format(xqueryExpression, korisnikEmail);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    zahtevi = (Zahtevi) unmarshaller.unmarshal(res.getContentAsDOM());
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

        return zahtevi;
    }

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
            zahtev.setAbout("http://localhost:8080/zahtevcir/" + UUID.randomUUID().toString().replace("-", ""));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            zahtev.getInformacijeOTraziocu().setRel("pred:createdBy");
            zahtev.getInformacijeOTraziocu().setHref(k.getAbout());
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
