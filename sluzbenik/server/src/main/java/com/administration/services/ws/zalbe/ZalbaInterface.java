package com.administration.services.ws.zalbe;

import com.administration.services.model.ObjectFactory;
import com.administration.services.model.Zalbacutanje;
import com.administration.services.model.Zalbanaodluku;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://poverenik/ws/zalba", name = "Zalba")
@XmlSeeAlso(ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ZalbaInterface {

    @WebMethod
    public void sendZalbaCutanje(
            @WebParam(partName = "zalbacutanje", name = "zalbacutanje")
                    Zalbacutanje zalbacutanje
    );

    @WebMethod
    public void sendZalbaOdluka(
            @WebParam(partName = "zalbanaodluku", name = "zalbanaodluku")
                    Zalbanaodluku zalbanaodluku
    );

    @WebMethod
    @WebResult
    public void sendOdgovor(
            @WebParam(partName = "odgovor", name = "odgovor")
                    String odgovor
    );
}
