package com.administration.services.helpers;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;

/**
 * 
 * Primer demonstrira koriscenje Apache FOP programskog API-a za 
 * renderovanje PDF-a primenom XSL-FO transformacije na XML dokumentu.
 *
 */
@Component
public class XSLFOTransformer {
	
	private FopFactory fopFactory;
	
	private TransformerFactory transformerFactory;

	private FOUserAgent userAgent;
	
	public static final String INPUT_FILE = "src/main/resources/test/fo/zahtevcir.xml";
	
	public static final String XSL_FILE = "src/main/resources/test/fo/zahtevcir.xsl";
	
	public static final String OUTPUT_FILE = "src/main/resources/test/fo/zahtevcir.pdf";

	@Bean
	private void setUpTransformer() throws SAXException, IOException {
		fopFactory = FopFactory.newInstance(new File("src/main/resources/fop.xconf"));
		transformerFactory = new TransformerFactoryImpl();
		userAgent = fopFactory.newFOUserAgent();
	}

	public void generatePDF() throws Exception {
		File xslFile = new File(XSL_FILE);
		StreamSource transformSource = new StreamSource(xslFile);
		StreamSource source = new StreamSource(new File(INPUT_FILE));

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());

		xslFoTransformer.transform(source, res);

		File pdfFile = new File(OUTPUT_FILE);
		if (!pdfFile.getParentFile().exists()) {
			pdfFile.getParentFile().mkdir();
		}

		OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		out.write(outStream.toByteArray());
		out.close();
	}
}
