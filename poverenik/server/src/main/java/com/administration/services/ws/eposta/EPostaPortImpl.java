package com.administration.services.ws.eposta;

import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.logging.Logger;

@WebService(
      serviceName = "EPostaService",
      portName = "EPostaPort",
      targetNamespace = "http://administracija/ws/eposta",
      endpointInterface = "com.administration.mailservice.ws.eposta.EPosta")
@Service
public class EPostaPortImpl implements EPosta {

    private static final Logger LOG = Logger.getLogger(EPostaPortImpl.class.getName());

    @Override
    public void sendMail(String subjekt, String tekst, String primalac) {
    }

    @Override
    public void sendMailMultipart(String subjekt, String tekst, String primalac, byte[] prilog) {
    }
}
