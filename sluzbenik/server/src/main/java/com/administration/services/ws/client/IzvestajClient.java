package com.administration.services.ws.client;

import com.administration.services.model.Izvestaj;
import com.administration.services.ws.izvestaj.IzvestajInterface;
import com.administration.services.ws.zalbe.ZalbaInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class IzvestajClient {

    public void sendIzvestaj(Izvestaj izvestaj) {
        try {
            URL wsdlLocation = new URL("http://localhost:8080/ws/izvestaj?wsdl");
            QName serviceName = new QName("http://administracija/ws/izvestaj", "IzvestajService");
            QName portName = new QName("http://administracija/ws/izvestaj", "IzvestajPort");

            Service service = Service.create(wsdlLocation, serviceName);

            IzvestajInterface izvestajService = service.getPort(portName, IzvestajInterface.class);

            izvestajService.sendIzvestaj(izvestaj);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
