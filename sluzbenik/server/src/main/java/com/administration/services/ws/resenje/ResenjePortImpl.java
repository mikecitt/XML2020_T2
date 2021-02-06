package com.administration.services.ws.resenje;

import com.administration.services.model.Resenje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.logging.Logger;

@WebService(
      serviceName = "ResenjeService",
      portName = "ResenjePort",
      targetNamespace = "http://administracija/ws/resenje",
      endpointInterface = "com.administration.services.ws.resenje.ResenjeInterface")
@Service
public class ResenjePortImpl implements ResenjeInterface {

    private static final Logger LOG = Logger.getLogger(ResenjePortImpl.class.getName());

    @Autowired
    private com.administration.services.business.ResenjeService resenjeService;

    @Override
    public void sendResenje(Resenje resenje) {
        LOG.info("Executing operation sendResenje");

        try {
            resenjeService.addNewResenje(resenje);
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
