package com.administration.services.ws.zalbe;

import com.administration.services.business.ZalbaService;
import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;
import com.administration.services.model.Zalbenaodluku;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.Vector;
import java.util.logging.Logger;

@WebService(
      serviceName = "ZalbaService",
      portName = "ZalbaPort",
      targetNamespace = "http://poverenik/ws/zalba",
     // wsdlLocation = "classpath:wsdl/Hello.wsdl",
      endpointInterface = "com.administration.services.ws.zalbe.ZalbaInterface")

public class ZalbaPortImpl implements ZalbaInterface {

    private static final Logger LOG = Logger.getLogger(ZalbaPortImpl.class.getName());

    /*@Autowired
    private ZalbaService zalbaService;*/

    @Override
    public void sendZalbaCutanje(Zalbacutanje zalbacutanje) {}

    @Override
    public void sendZalbaOdluka(Zalbanaodluku zalbanaodluku) {}

    @Override
    public void sendOdgovor(String odgovor, String zalbaId) {
        LOG.info("Executing operation sendOdgovor");

        System.out.print(zalbaId + " ");
        System.out.println(odgovor);
    }
}
