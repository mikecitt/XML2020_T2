package com.administration.services.business;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.model.Izvestaj;
import com.administration.services.model.Korisnik;
import com.administration.services.model.Izvestaj.DatumPodnosenja;
import com.administration.services.model.Izvestaj.Podnosilac;
import com.administration.services.ws.client.IzvestajClient;

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
public class IzvestajService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private IzvestajClient izvestajClient;

    @Autowired
    private KorisnikService korisnikService;

    @Value("/db/sluzbenik")
    private String collectionId;

    @Value("resenje.xml")
    private String resenjeId;

    public void makeIzvestaj() throws Exception {
        Izvestaj izvestaj = null;

        Collection col = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/makeIzvestaj.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, resenjeId);
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
                    izvestaj.setVocab("http://localhost:8080/rdf/predicate/");
                    izvestaj.setAbout(
                            "http://localhost:8080/izvestaj/" + UUID.randomUUID().toString().replace("-", ""));
                    DatumPodnosenja datumPodnosenja = new DatumPodnosenja();
                    datumPodnosenja.setValue(new Date());
                    izvestaj.setDatumPodnosenja(datumPodnosenja);
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
                    Podnosilac p = new Podnosilac();
                    p.setIme(k.getIme());
                    p.setPrezime(k.getPrezime());
                    p.setRel("pred:createdBy");
                    p.setHref(k.getAbout());
                    izvestaj.setPodnosilac(p);
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

        izvestajClient.sendIzvestaj(izvestaj);
    }
}
