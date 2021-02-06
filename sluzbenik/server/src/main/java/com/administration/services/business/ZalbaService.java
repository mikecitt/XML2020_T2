package com.administration.services.business;

import com.administration.services.configs.ExistConfiguration;
import com.administration.services.configs.JenaConfiguration;
import com.administration.services.enums.Status;
import com.administration.services.enums.XslDocumentsPaths;
import com.administration.services.helpers.DefaultNamespacePrefixMapper;
import com.administration.services.helpers.XSLFOTransformer;
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
import java.io.ByteArrayOutputStream;
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

    @Value("/db/sluzbenik")
    private String collectionId;

    @Value("zalbacutanje.xml")
    private String zalbaCutanjeId;

    @Value("zalbanaodluku.xml")
    private String zalbaOdlukuId;

    public boolean doesExistZalba(String zalbaId) {
        return getOneZalba(zalbaId) != null;
    }

    public Zalbacutanje getZalbaCutanje(String zalbaId) throws Exception {
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

    public Zalbanaodluku getZalbaOdluku(String zalbaId) throws Exception {
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

    public boolean odgovoriNaZalbuCutanje(String zalbaId) throws Exception {
        Zalbecutanje zalbecutanje = getAllZalbeCutanje();
        for(Zalbacutanje zal : zalbecutanje.getZalbacutanje()) {
            if(zal.getAbout().equals("http://localhost:8080/zalba/" + zalbaId)) {
                zal.getStatus().setValue(Status.GOTOV.toString());
            }
        }

        return true;
    }

    public boolean odgovoriNaZalbuOdluku(String zalbaId) throws Exception {
        Zalbenaodluku zalbenaodluku = getAllZalbeOdluka();
        for(Zalbanaodluku zal : zalbenaodluku.getZalbanaodluku()) {
            if(zal.getAbout().equals("http://localhost:8080/zalbanaodluku/" + zalbaId)) {
                zal.getStatus().setValue(Status.GOTOV.toString());
            }
        }

        return true;
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
}
