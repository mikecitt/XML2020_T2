package com.administration.services.business;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.dto.odgovor.OdgovorSluzbenika;
import com.administration.services.dto.odgovor.Odgovori;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Date;
import java.util.UUID;

@Service
public class OdgovorService {

    private static final int WAIT_DURATION_IN_MILLIES = 300000; // 5 minuta

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Autowired
    private ZalbaService zalbaService;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("odgovori.xml")
    private String odgovorId;

    public boolean checkOdgovorIsticanje(String zalbaId) {
        Collection col = null;
        OdgovorSluzbenika odg = null;
        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getOdgovorFromZalba.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, zalbaId);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.dto.odgovor");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    odg = (OdgovorSluzbenika) unmarshaller.unmarshal(res.getContentAsDOM());
                    Date d = new Date();
                    if(!odg.isOdgovorio() && odg.getDatumZahtevanja().getTime() + WAIT_DURATION_IN_MILLIES <= d.getTime()) {
                        return true;
                    }
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

        return false;
    }

    public void placeOdgovor(String zalbaId, String odgovor) {
        Collection col = null;
        XMLResource res = null;
        Odgovori odgovori = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            col = existConfiguration.getOrCreateCollection(collectionId);

            res = (XMLResource) col.createResource(odgovorId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.dto.odgovor");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try {
                odgovori = (Odgovori) unmarshaller.unmarshal(res.getContentAsDOM());
                for(OdgovorSluzbenika odg : odgovori.getOdgovori()) {
                    if(odg.getHref().contains(zalbaId)) {
                        odg.setOdgovorio(true);
                        odg.setOdgovor(odgovor);
                    }
                }
            } catch (XMLDBException ignored) {
            }

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, odgovori);

            res.setContent(os);
            col.storeResource(res);
            jenaConfiguration.updateRDF(new String(os.toByteArray(), StandardCharsets.UTF_8));

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
    }

    public void addZahtevOdgovora(String zalbaId, String tipZalbe) throws Exception {
        Collection col = null;
        XMLResource res = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {

            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(odgovorId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.dto.odgovor");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Odgovori odgovori = null;
            try {
                odgovori = (Odgovori) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                odgovori = new Odgovori();
            }
            OdgovorSluzbenika odg = new OdgovorSluzbenika();
            odg.setOdgovor("");
            odg.setOdgovorio(false);
            odg.setRel("pred:answerFor");
            odg.setHref("http://localhost:8080/" + tipZalbe + "/" + zalbaId);
            odg.setDatumZahtevanja(new Date());
            odg.setVocab("http://localhost:8080/rdf/predicate/");
            odg.setAbout("http://localhost:8080/odgovori/" + UUID.randomUUID().toString().replace("-", ""));
            odgovori.getOdgovori().add(odg);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
            existConfiguration.prepareForWriting(marshaller, os, odgovori);

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
    }
}
