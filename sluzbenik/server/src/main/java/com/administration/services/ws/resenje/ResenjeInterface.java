package com.administration.services.ws.resenje;

import com.administration.services.model.ObjectFactory;
import com.administration.services.model.Resenje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://administracija/ws/resenje", name = "Resenje")
@XmlSeeAlso(ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ResenjeInterface {

    @WebMethod
    public void sendResenje(
            @WebParam(partName = "resenje", name = "resenje")
                    Resenje resenje
    );
}
