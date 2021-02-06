package com.administration.services.ws.izvestaj;

import com.administration.services.business.IzvestajService;
import com.administration.services.model.Izvestaj;
import com.administration.services.model.Resenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.logging.Logger;

@WebService(serviceName = "IzvestajService", portName = "IzvestajPort", targetNamespace = "http://administracija/ws/izvestaj", endpointInterface = "com.administration.services.ws.izvestaj.IzvestajInterface")
@Service
public class IzvestajPortImpl implements IzvestajInterface {

    private static final Logger LOG = Logger.getLogger(IzvestajPortImpl.class.getName());

    @Autowired
    private IzvestajService izvestajService;

    @Override
    public void sendIzvestaj(Izvestaj izvestaj) {
        try {
            izvestajService.addIzvestaj(izvestaj);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
