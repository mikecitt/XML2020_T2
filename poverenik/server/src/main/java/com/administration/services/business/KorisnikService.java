package com.administration.services.business;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.enums.TipKorisnika;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.model.Korisnici;
import com.administration.services.model.Korisnik;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class KorisnikService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("korisnici.xml")
    private String korisniciId;

    public Korisnik getKorisnikByEmail(String email) {
        Collection col = null;
        Korisnik korisnikDetail = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getOneKorisnik.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, email);
            System.out.println(xqueryExpression);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if(i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource)i.nextResource();
                    korisnikDetail = (Korisnik) unmarshaller.unmarshal(res.getContentAsDOM());
                } finally {
                    try {
                        ((EXistResource)res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return korisnikDetail;
    }

    public Korisnik addNewKorisnik(Korisnik korisnik) throws Exception {
        Collection col = null;
        XMLResource res = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Korisnik createdKorisnik = null;

        if(getKorisnikByEmail(korisnik.getEmailAdresa().getValue()) != null) {
            throw new Exception("User exist");
        }

        try {

            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(korisniciId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Korisnici korisnici = null;
            try {
                korisnici = (Korisnici) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                korisnici = new Korisnici();
            }

            korisnik.setSifra(passwordEncoder.encode(korisnik.getSifra()));
            korisnik.setTipKorisnika(TipKorisnika.GRADJANIN.toString());
            prepareKorisnik(korisnik);
            korisnici.getKorisnik().add(korisnik);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, korisnici);

            res.setContent(os);
            col.storeResource(res);
            jenaConfiguration.updateRDF(new String(os.toByteArray(), StandardCharsets.UTF_8));

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
        createdKorisnik = korisnik;
        return createdKorisnik;
    }

    private void prepareKorisnik(Korisnik korisnik) {
        korisnik.setAbout("http://localhost:8080/korisnici/" + korisnik.getEmailAdresa().getValue());
        korisnik.setVocab("http://localhost:8080/rdf/predicate/");
        korisnik.getEmailAdresa().setDatatype("tip:TemailAdresa");
        korisnik.getEmailAdresa().setProperty("pred:email_adresa");
    }
}
