package com.administration.services.helpers;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.administration.services.enums.XsltDocumentsPaths;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class XSLTTransformer {

    private DocumentBuilderFactory documentFactory;

    private TransformerFactory transformerFactory;

    @Bean
    private void setUpTransformerHTML() {
        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();
    }

    public org.w3c.dom.Document buildDocument(String file) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(file.getBytes()));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    public byte[] generateHTML(XsltDocumentsPaths docType, String xmlOutput) {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(docType.toString()));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlOutput));
            Result result = new StreamResult(os);
            transformer.transform(source, result);

            return os.toByteArray();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
