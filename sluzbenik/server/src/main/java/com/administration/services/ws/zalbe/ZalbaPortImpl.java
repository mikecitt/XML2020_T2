package com.administration.services.ws.zalbe;

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
      targetNamespace = "http://administracija/ws/zalba",
      endpointInterface = "com.administration.services.ws.zalbe.ZalbaInterface")

public class ZalbaPortImpl implements ZalbaInterface {

    private static final Logger LOG = Logger.getLogger(ZalbaPortImpl.class.getName());

    /*@Autowired
    private ZalbaService zalbaService;*/

    private Vector<Zalbacutanje> cutanje = new Vector<Zalbacutanje>();
    private Vector<Zalbanaodluku> odluke = new Vector<Zalbanaodluku>();

    @Override
    public void sendZalbaCutanje(Zalbacutanje zalbacutanje) {
        LOG.info("Executing operation sendZalbaCutanje");

        try {
            cutanje.add(zalbacutanje);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void sendZalbaOdluka(Zalbanaodluku zalbanaodluku) {
        LOG.info("Executing operation sendZalbaOdluka");

        try {
            odluke.add(zalbanaodluku);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void sendOdgovor(String odgovor, String zalbaId) {}
}
