package com.administration.services.ws.eposta;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://administracija/ws/eposta", name = "Eposta")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface EPosta {

    @WebMethod
    public void sendMail(
            @WebParam(partName = "subjekt", name = "subjekt")
                    String subjekt,
            @WebParam(partName = "tekst", name = "tekst")
                    String tekst,
            @WebParam(partName = "primalac", name = "primalac")
                    String primalac
    );

    @WebMethod
    public void sendMailMultipart(
            @WebParam(partName = "subjekt", name = "subjekt")
                    String subjekt,
            @WebParam(partName = "tekst", name = "tekst")
                    String tekst,
            @WebParam(partName = "primalac", name = "primalac")
                    String primalac,
            @WebParam(partName = "prilog", name = "prilog")
                    byte[] prilog
    );
}
