package com.administration.services.ws.client;

import com.administration.services.model.Resenje;
import com.administration.services.ws.resenje.ResenjeInterface;
import com.administration.services.ws.zalbe.ZalbaInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class ResenjeClient {

    public void sendResenje(Resenje resenje) {
        try {
            URL wsdlLocation = new URL("http://localhost:8082/ws/resenje?wsdl");
            QName serviceName = new QName("http://administracija/ws/resenje", "ResenjeService");
            QName portName = new QName("http://administracija/ws/resenje", "ResenjePort");

            Service service = Service.create(wsdlLocation, serviceName);

            ResenjeInterface resenjeService = service.getPort(portName, ResenjeInterface.class);

            resenjeService.sendResenje(resenje);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
