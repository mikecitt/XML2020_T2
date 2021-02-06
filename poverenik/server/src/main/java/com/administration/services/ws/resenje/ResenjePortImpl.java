package com.administration.services.ws.resenje;

import com.administration.services.model.Resenje;
import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;
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

    @Override
    public void sendResenje(Resenje resenje) {}
}
