package com.administration.services.ws.izvestaj;

import com.administration.services.model.Izvestaj;
import com.administration.services.model.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://administracija/ws/izvestaj", name = "Izvestaj")
@XmlSeeAlso(ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IzvestajInterface {

    @WebMethod
    public void sendIzvestaj(
            @WebParam(partName = "izvestaj", name = "izvestaj")
                    Izvestaj izvestaj
    );
}
