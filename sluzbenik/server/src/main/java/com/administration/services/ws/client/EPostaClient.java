package com.administration.services.ws.client;

import com.administration.services.ws.eposta.EPosta;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class EPostaClient {

    public void sendMail(String subjekt, String tekst, String primalac) {
        try {
            URL wsdlLocation = new URL("http://localhost:8081/ws/eposta?wsdl");
            QName serviceName = new QName("http://administracija/ws/eposta", "EPostaService");
            QName portName = new QName("http://administracija/ws/eposta", "EPostaPort");

            Service service = Service.create(wsdlLocation, serviceName);

            EPosta ePostaService = service.getPort(portName, EPosta.class);

            ePostaService.sendMail(subjekt, tekst, primalac);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void sendMail(String subjekt, String tekst, String primalac, byte[] prilog) {
        try {
            URL wsdlLocation = new URL("http://localhost:8082/ws/eposta?wsdl");
            QName serviceName = new QName("http://administracija/ws/eposta", "EPostaService");
            QName portName = new QName("http://administracija/ws/eposta", "EPostaPort");

            Service service = Service.create(wsdlLocation, serviceName);

            EPosta ePostaService = service.getPort(portName, EPosta.class);

            ePostaService.sendMailMultipart(subjekt, tekst, primalac, prilog);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
