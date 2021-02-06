package com.administration.services.ws.zalbe;

import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;
import java.util.logging.Logger;

@WebService(
      serviceName = "ZalbaService",
      portName = "ZalbaPort",
      targetNamespace = "http://administracija/ws/zalba",
      endpointInterface = "com.administration.services.ws.zalbe.ZalbaInterface")
@Service
public class ZalbaPortImpl implements ZalbaInterface {

    private static final Logger LOG = Logger.getLogger(ZalbaPortImpl.class.getName());

    @Autowired
    private com.administration.services.business.ZalbaService zalbaService;

    @Override
    public void sendZalbaCutanje(Zalbacutanje zalbacutanje) {
        LOG.info("Executing operation sendZalbaCutanje");

        try {
            zalbaService.addNewZalbaCutanje(zalbacutanje);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void sendZalbaOdluka(Zalbanaodluku zalbanaodluku) {
        LOG.info("Executing operation sendZalbaOdluka");

        try {
            zalbaService.addNewZalbaOdluka(zalbanaodluku);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void sendOdgovor(String odgovor, String zalbaId) {}
}
