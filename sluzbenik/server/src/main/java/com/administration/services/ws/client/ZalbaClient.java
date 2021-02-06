package com.administration.services.ws.client;

import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;
import com.administration.services.ws.zalbe.ZalbaInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class ZalbaClient {

    public void sendZalbaCutanje(String odgovor, String zalbaId) {
        try {
            URL wsdlLocation = new URL("http://localhost:8080/ws/zalbaOdgovor?wsdl");
            QName serviceName = new QName("http://administracija/ws/zalba", "ZalbaService");
            QName portName = new QName("http://administracija/ws/zalba", "ZalbaPort");

            Service service = Service.create(wsdlLocation, serviceName);

            ZalbaInterface zalbaService = service.getPort(portName, ZalbaInterface.class);

            zalbaService.sendOdgovor(odgovor, zalbaId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
