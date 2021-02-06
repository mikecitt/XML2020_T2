package com.administration.services.ws.resenje;

import com.administration.services.model.Resenje;
import com.administration.services.model.Zalbacutanje;

import javax.jws.WebService;
import java.util.Vector;
import java.util.logging.Logger;

@WebService(
      serviceName = "ResenjeService",
      portName = "ResenjePort",
      targetNamespace = "http://administracija/ws/resenje",
      endpointInterface = "com.administration.services.ws.resenje.ResenjeInterface")

public class ResenjePortImpl implements ResenjeInterface {

    private static final Logger LOG = Logger.getLogger(ResenjePortImpl.class.getName());

    private Vector<Resenje> resenja = new Vector<Resenje>();

    @Override
    public void sendResenje(Resenje resenje) {
        LOG.info("Executing operation sendResenje");

        try {
            resenja.add(resenje);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
