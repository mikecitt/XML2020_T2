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

    public void sendZalbaCutanje(Zalbacutanje zalbacutanje) {
        try {
            URL wsdlLocation = new URL("http://localhost:8082/ws/zalbaOdgovor?wsdl");
            QName serviceName = new QName("http://poverenik/ws/zalba", "ZalbaService");
            QName portName = new QName("http://poverenik/ws/zalba", "ZalbaPort");

            Service service = Service.create(wsdlLocation, serviceName);

            ZalbaInterface zalbaService = service.getPort(portName, ZalbaInterface.class);

            zalbaService.sendZalbaCutanje(zalbacutanje);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void sendZalbaOdluku(Zalbanaodluku zalbanaodluku) {
        try {
            URL wsdlLocation = new URL("http://localhost:8082/ws/zalbaOdgovor?wsdl");
            QName serviceName = new QName("http://poverenik/ws/zalba", "ZalbaService");
            QName portName = new QName("http://poverenik/ws/zalba", "ZalbaPort");

            Service service = Service.create(wsdlLocation, serviceName);

            ZalbaInterface zalbaService = service.getPort(portName, ZalbaInterface.class);

            zalbaService.sendZalbaOdluka(zalbanaodluku);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
