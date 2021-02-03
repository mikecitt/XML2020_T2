package com.administration.services.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.administration.services.model.Resenja;
import com.administration.services.model.Resenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
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

@Service
public class ResenjeService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("resenje.xml")
    private String resenjeId;

    public Resenja getAllResenja() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Resenja resenja = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId);

            res = (XMLResource) col.createResource(resenjeId, XMLResource.RESOURCE_TYPE);
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

    public void addNewResenje(Resenje resenje, Korisnik korisnik, String aboutZalba) throws Exception {
        Collection col = null;
        XMLResource res = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(resenjeId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Resenja resenja = null;
            try {
                resenja = (Resenja) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                resenja = new Resenja();
            }

            prepareResenje(resenje, korisnik);
            resenje.getRazlogZalbe().setRel("pred:refTo");
            resenje.getRazlogZalbe().setHref("http://localhost:8080/" + aboutZalba);
            resenja.getResenje().add(resenje);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, resenja);

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

    private void prepareResenje(Resenje resenje, Korisnik korisnik) {
        resenje.setAbout("http://localhost:8080/resenje/" +
                UUID.randomUUID().toString().replace("-", ""));
        resenje.setVocab("http://localhost:8080/rdf/predicate/");
        resenje.getPoverenik().setRel("pred:createdBy");
        resenje.getPoverenik().setHref(korisnik.getAbout());
        resenje.getZaglavlje().getInformacijePredmeta()
                .getDatumPredmeta().setDatatype("tip:TdatumBroj");
        resenje.getZaglavlje().getInformacijePredmeta()
                .getDatumPredmeta().setProperty("pred:datum_predmeta");
        resenje.getZaglavlje().getDatumResenja().setDatatype("tip:TdatumBroj");
        resenje.getZaglavlje().getDatumResenja().setProperty("pred:datum_resenja");
        resenje.getTeloResenja().getUpravniSpor().getMesto().setDatatype("xs:string");
        resenje.getTeloResenja().getUpravniSpor().getMesto().setProperty("pred:mesto_spora");
    }
}
