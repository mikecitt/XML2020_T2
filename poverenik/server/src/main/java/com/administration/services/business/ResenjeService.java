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
import com.administration.services.enums.Status;
import com.administration.services.enums.XslDocumentsPaths;
import com.administration.services.enums.XsltDocumentsPaths;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.helpers.XSLFOTransformer;
import com.administration.services.helpers.XSLTTransformer;
import com.administration.services.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.modules.XQueryService;

@Service
public class ResenjeService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Autowired
    private ZalbaService zalbaService;

    @Autowired
    private XSLFOTransformer transformer;

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("resenje.xml")
    private String resenjeId;

    public Resenje getResenje(String id, String queryPath) {
        Collection col = null;
        Resenje resenje = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, id);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    resenje = (Resenje) unmarshaller.unmarshal(res.getContentAsDOM());
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

        return resenje;
    }

    public Resenje getOneResenjeFromZalba(String zalbaId, String korisnikId) throws Exception {
        if (korisnikId != null && !zalbaService.doesZalbaBelongToKorisnik(zalbaId, korisnikId)) {
            throw new Exception("Zalba not belong to Korisnik");
        }

        return getResenje(zalbaId, "src/main/resources/xquery/getResenjeFromZalba.xqy");
    }

    public Resenje getOneResenje(String resenjeId) {
        return getResenje(resenjeId, "src/main/resources/xquery/getOneResenje.xqy");
    }

    public byte[] getOneResenjePDF(Resenje resenje) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, resenje);

        return transformer.generatePDF(XslDocumentsPaths.RESENJE, new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public byte[] getOneResenjeHTML(Resenje resenje) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, resenje);

        return xsltTransformer.generateHTML(XsltDocumentsPaths.RESENJE,
                new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

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

    public void addNewResenje(Resenje resenje, Korisnik korisnik, String zalbaId) throws Exception {
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

            Object zalba = zalbaService.getOneZalba(zalbaId);
            if (zalba == null) {
                throw new Exception("No existing Zalba");
            }

            if (getOneResenjeFromZalba(zalbaId, null) != null) {
                throw new Exception("Zalba already has Resenje");
            }

            prepareResenje(resenje, korisnik);
            resenje.getRazlogZalbe().setRel("pred:refTo");
            if (zalba instanceof Zalbanaodluku) {
                resenje.getRazlogZalbe().setHref(((Zalbanaodluku) zalba).getAbout());
                zalbaService.odgovoriNaZalbuOdluku(zalbaId, Status.GOTOV);
            } else {
                resenje.getRazlogZalbe().setHref(((Zalbacutanje) zalba).getAbout());
                zalbaService.odgovoriNaZalbuCutanje(zalbaId, Status.GOTOV);
            }
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
        resenje.setAbout("http://localhost:8080/resenje/" + UUID.randomUUID().toString().replace("-", ""));
        resenje.setVocab("http://localhost:8080/rdf/predicate/");
        resenje.getPoverenik().setRel("pred:createdBy");
        resenje.getPoverenik().setHref(korisnik.getAbout());
        resenje.getZaglavlje().getInformacijePredmeta().getDatumPredmeta().setDatatype("tip:TdatumBroj");
        resenje.getZaglavlje().getInformacijePredmeta().getDatumPredmeta().setProperty("pred:datum_predmeta");
        resenje.getZaglavlje().getDatumResenja().setDatatype("tip:TdatumBroj");
        resenje.getZaglavlje().getDatumResenja().setProperty("pred:datum_resenja");
        resenje.getTeloResenja().getUpravniSpor().getMesto().setDatatype("xs:string");
        resenje.getTeloResenja().getUpravniSpor().getMesto().setProperty("pred:mesto_spora");
    }
}
