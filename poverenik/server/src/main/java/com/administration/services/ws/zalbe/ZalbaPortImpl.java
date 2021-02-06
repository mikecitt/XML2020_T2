package com.administration.services.ws.zalbe;

import com.administration.services.business.OdgovorService;
import com.administration.services.business.ZalbaService;
import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;
import com.administration.services.model.Zalbenaodluku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.Vector;
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
    private OdgovorService odgovorService;

    @Override
    public void sendZalbaCutanje(Zalbacutanje zalbacutanje) {}

    @Override
    public void sendZalbaOdluka(Zalbanaodluku zalbanaodluku) {}

    @Override
    public void sendOdgovor(String odgovor, String zalbaId) {
        LOG.info("Executing operation sendOdgovor");

        if(!odgovorService.checkOdgovorIsticanje(zalbaId)) {
            odgovorService.placeOdgovor(zalbaId, odgovor);
        }
    }
}
