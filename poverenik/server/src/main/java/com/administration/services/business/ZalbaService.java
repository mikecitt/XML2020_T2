package com.administration.services.business;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.enums.Status;
import com.administration.services.enums.XslDocumentsPaths;
import com.administration.services.enums.XsltDocumentsPaths;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.helpers.XSLFOTransformer;
import com.administration.services.helpers.XSLTTransformer;
import com.administration.services.model.*;
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
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ZalbaService {

    @Autowired
    private ExistConfiguration existConfiguration;

    @Autowired
    private JenaConfiguration jenaConfiguration;

    @Autowired
    private XSLFOTransformer transformer;

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("/db/poverenik")
    private String collectionId;

    @Value("zalbacutanje.xml")
    private String zalbaCutanjeId;

    @Value("zalbanaodluku.xml")
    private String zalbaOdlukuId;

    public boolean doesExistZalba(String zalbaId) {
        return getOneZalba(zalbaId) != null;
    }

    public Zalbacutanje getZalbaCutanje(String zalbaId, String korisnikId) throws Exception {
        if (korisnikId != null && !doesZalbaBelongToKorisnik(zalbaId, korisnikId)) {
            throw new Exception("Zalba not belong to Korisnik");
        }
        return (Zalbacutanje) getOneZalba(zalbaId);
    }

    public byte[] getZalbaCutanjePDF(Zalbacutanje zalbacutanje) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, zalbacutanje);

        return transformer.generatePDF(XslDocumentsPaths.ZALBACUTANJE,
                new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public byte[] getZalbaCutanjeHTML(Zalbacutanje zalbacutanje) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, zalbacutanje);

        return xsltTransformer.generateHTML(XsltDocumentsPaths.ZALBACUTANJE,
                new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public Zalbanaodluku getZalbaOdluku(String zalbaId, String korisnikId) throws Exception {
        if (korisnikId != null && !doesZalbaBelongToKorisnik(zalbaId, korisnikId)) {
            throw new Exception("Zalba not belong to Korisnik");
        }
        return (Zalbanaodluku) getOneZalba(zalbaId);
    }

    public byte[] getZalbaOdlukuPDF(Zalbanaodluku zalbanaodluku) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, zalbanaodluku);

        return transformer.generatePDF(XslDocumentsPaths.ZALBAODLUKA,
                new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public byte[] getZalbaOdlukuHTML(Zalbanaodluku zalbanaodluku) throws Exception {
        JAXBContext context = JAXBContext.newInstance("com.administration.services.model");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

        existConfiguration.prepareForWriting(marshaller, os, zalbanaodluku);

        return xsltTransformer.generateHTML(XsltDocumentsPaths.ZALBAODLUKA,
                new String(os.toByteArray(), StandardCharsets.UTF_8));
    }

    public Object getOneZalba(String zalbaId) {
        Collection col = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getOneZalba.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, zalbaId);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    return unmarshaller.unmarshal(res.getContentAsDOM());
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

        return null;
    }

    public boolean doesZalbaBelongToKorisnik(String zalbaId, String korisnikId) {
        Collection col = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get("src/main/resources/xquery/getKorisnikZalba.xqy"));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, korisnikId, zalbaId);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    return unmarshaller.unmarshal(res.getContentAsDOM()) != null;
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

    public Zalbecutanje getKorisnikZalbeCutanje(String korisnikId) {
        return (Zalbecutanje) getKorisnikZalbe(korisnikId, "src/main/resources/xquery/getKorisnikZalbeCutanje.xqy");
    }

    public Zalbenaodluku getKorisnikZalbaOdluku(String korisnikId) {
        return (Zalbenaodluku) getKorisnikZalbe(korisnikId, "src/main/resources/xquery/getKorisnikZalbeOdluka.xqy");
    }

    public Object getKorisnikZalbe(String korisnikId, String queryPath) {
        Collection col = null;

        try {
            col = existConfiguration.getOrCreateCollection(collectionId, 0);
            XQueryService xqueryService = (XQueryService) col.getService("XQueryService", "1.0");
            xqueryService.setProperty("indent", "yes");

            byte[] encoded = Files.readAllBytes(Paths.get(queryPath));
            String xqueryExpression = new String(encoded, StandardCharsets.UTF_8);
            xqueryExpression = String.format(xqueryExpression, korisnikId);
            CompiledExpression compiledXquery = xqueryService.compile(xqueryExpression);
            ResourceSet result = xqueryService.execute(compiledXquery);

            ResourceIterator i = result.getIterator();
            XMLResource res = null;

            if (i.hasMoreResources()) {
                try {
                    JAXBContext context = JAXBContext.newInstance("com.administration.services.model");

                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    res = (XMLResource) i.nextResource();
                    return unmarshaller.unmarshal(res.getContentAsDOM());
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

        return null;
    }

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

    public void addNewZalbaCutanje(Zalbacutanje zalbacutanje, Korisnik korisnik) throws Exception {
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
            prepareZalbCut(zalbacutanje, korisnik);
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

    public void addNewZalbaOdluka(Zalbanaodluku zalbanaodluku, Korisnik korisnik) throws Exception {
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
            prepareZalbOd(zalbanaodluku, korisnik);
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

    private void prepareZalbOd(Zalbanaodluku zalbanaodluku, Korisnik korisnik) {
        Zalbanaodluku.Status status = new Zalbanaodluku.Status();
        status.setDatatype("xs:string");
        status.setProperty("pred:status");
        status.setValue(Status.OBRADA.toString());
        zalbanaodluku.setStatus(status);
        zalbanaodluku.setAbout("http://localhost:8080/zalbanaodluku/" + UUID.randomUUID().toString().replace("-", ""));
        zalbanaodluku.setVocab("http://localhost:8080/rdf/predicate/");
        zalbanaodluku.getInformacijeOPodnosiocuZalbe().setRel("pred:createdBy");
        zalbanaodluku.getInformacijeOPodnosiocuZalbe().setHref(korisnik.getAbout());
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

    private void prepareZalbCut(Zalbacutanje zalbacutanje, Korisnik korisnik) {
        Zalbacutanje.Status status = new Zalbacutanje.Status();
        status.setDatatype("xs:string");
        status.setProperty("pred:status");
        status.setValue(Status.OBRADA.toString());
        zalbacutanje.setStatus(status);
        zalbacutanje.setAbout("http://localhost:8080/zalba/" + UUID.randomUUID().toString().replace("-", ""));
        zalbacutanje.setVocab("http://localhost:8080/rdf/predicate/");
        zalbacutanje.getInformacijeOPodnosiocuZalbe().setRel("pred:createdBy");
        zalbacutanje.getInformacijeOPodnosiocuZalbe().setHref(korisnik.getAbout());
        zalbacutanje.getNazivOrgana().setDatatype("xs:string");
        zalbacutanje.getNazivOrgana().setProperty("pred:naziv_organa");
        zalbacutanje.getDetaljiPredaje().getDatum().setDatatype("tip:Tdatum");
        zalbacutanje.getDetaljiPredaje().getDatum().setProperty("pred:datum_predaje");
        zalbacutanje.getDetaljiPredaje().getMesto().setDatatype("xs:string");
        zalbacutanje.getDetaljiPredaje().getMesto().setProperty("pred:mesto_predaje");
    }
}
