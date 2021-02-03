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
import com.administration.services.model.Korisnik;
import com.administration.services.model.Zahtev;
import com.administration.services.model.Zahtevi;
import com.administration.services.model.Zahtev.Status;

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
