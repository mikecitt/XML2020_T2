package com.administration.services.ws.izvestaj;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

@WebServiceClient(name = "IzvestajService",
                  wsdlLocation = "classpath:wsdl/Izvestaj.wsdl",
                  targetNamespace = "http://administracija/ws/izvestaj")
public class IzvestajService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://administracija/ws/izvestaj", "IzvestajService");
    public final static QName IzvestajPort = new QName("http://administracija/ws/izvestaj", "IzvestajPort");
    static {
        URL url = IzvestajService.class.getClassLoader().getResource("wsdl/Izvestaj.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(IzvestajService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/Izvestaj.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public IzvestajService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IzvestajService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IzvestajService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public IzvestajService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public IzvestajService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public IzvestajService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    @WebEndpoint(name = "IzvestajPort")
    public IzvestajInterface getIzvestajPort() {
        return super.getPort(IzvestajPort, IzvestajInterface.class);
    }

    @WebEndpoint(name = "IzvestajPort")
    public IzvestajInterface getIzvestajPort(WebServiceFeature... features) {
        return super.getPort(IzvestajPort, IzvestajInterface.class, features);
    }
}
