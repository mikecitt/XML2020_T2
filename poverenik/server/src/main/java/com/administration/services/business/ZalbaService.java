package com.administration.services.business;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.model.*;
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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class ZalbaService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

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
            col = existConfiguration.getOrCreateCollection(collectionId);
            res = (XMLResource) col.createResource(zalbaCutanjeId, XMLResource.RESOURCE_TYPE);
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
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(zalbaCutanjeId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zalbecutanje zalbecutanje = null;
            try {
                zalbecutanje = (Zalbecutanje) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zalbecutanje = new Zalbecutanje();
            }
            prepareZalbCut(zalbacutanje);
            zalbecutanje.getZalbacutanje().add(zalbacutanje);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, zalbecutanje);

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

    public Zalbenaodluku getAllZalbeOdluka() throws Exception {
        Collection col = null;
        XMLResource res = null;
        Zalbenaodluku zalbenaodluku = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId);
            res = (XMLResource) col.createResource(zalbaOdlukuId, XMLResource.RESOURCE_TYPE);
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
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            res = (XMLResource) col.createResource(zalbaOdlukuId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Zalbenaodluku zalbenaodluku = null;
            try {
                zalbenaodluku = (Zalbenaodluku) unmarshaller.unmarshal(res.getContentAsDOM());
            } catch (XMLDBException ex) {
                zalbenaodluku = new Zalbenaodluku();

            }
            prepareZalbOd(zalbanaodluku);
            zalbenaodluku.getZalbanaodluku().add(zalbanaodluku);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            existConfiguration.prepareForWriting(marshaller, os, zalbenaodluku);

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

    private void prepareZalbOd(Zalbanaodluku zalbanaodluku) {
        zalbanaodluku.setAbout("http://localhost:8080/zalbanaodluku/" +
                UUID.randomUUID().toString().replace("-", ""));
        zalbanaodluku.setVocab("http://localhost:8080/rdf/predicate/");
        zalbanaodluku.getNazivOrgana().setDatatype("xs:string");
        zalbanaodluku.getNazivOrgana().setProperty("pred:naziv_organa");
        zalbanaodluku.getResenje().getDatumResenja().setDatatype("tip:Tdatum");
        zalbanaodluku.getResenje().getDatumResenja().setProperty("pred:datum_resenja");
        zalbanaodluku.getDatumPodnosenjaZahteva().setDatatype("tip:Tdatum");
        zalbanaodluku.getDatumPodnosenjaZahteva().setProperty("pred:datum_podnosenja_zahteva");
        zalbanaodluku.getDetaljiPredaje().getDatum().setDatatype("tip:Tdatum");
        zalbanaodluku.getDetaljiPredaje().getDatum().setProperty("pred:datum_predaje");
        zalbanaodluku.getDetaljiPredaje().getMesto().setDatatype("xs:string");
        zalbanaodluku.getDetaljiPredaje().getMesto().setProperty("pred:mesto_predaje");
    }

    private void prepareZalbCut(Zalbacutanje zalbacutanje) {
        zalbacutanje.setAbout("http://localhost:8080/zalba/" +
                UUID.randomUUID().toString().replace("-", ""));
        zalbacutanje.setVocab("http://localhost:8080/rdf/predicate/");
        zalbacutanje.getNazivOrgana().setDatatype("xs:string");
        zalbacutanje.getNazivOrgana().setProperty("pred:naziv_organa");
        zalbacutanje.getDetaljiPredaje().getDatum().setDatatype("tip:Tdatum");
        zalbacutanje.getDetaljiPredaje().getDatum().setProperty("pred:datum_predaje");
        zalbacutanje.getDetaljiPredaje().getMesto().setDatatype("xs:string");
        zalbacutanje.getDetaljiPredaje().getMesto().setProperty("pred:mesto_predaje");
    }
}
