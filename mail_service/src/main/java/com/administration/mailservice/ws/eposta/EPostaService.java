package com.administration.mailservice.ws.eposta;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

@WebServiceClient(name = "EPostaService",
                  wsdlLocation = "classpath:wsdl/EPosta.wsdl",
                  targetNamespace = "http://administracija/ws/eposta")
public class EPostaService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://administracija/ws/eposta", "EPostaService");
    public final static QName EPostaPort = new QName("http://administracija/ws/eposta", "EPostaPort");
    static {
        URL url = EPostaService.class.getClassLoader().getResource("wsdl/EPosta.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(EPostaService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/EPosta.wsdl");
        }       
        WSDL_LOCATION = url;   
    }

    public EPostaService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EPostaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EPostaService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public EPostaService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public EPostaService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public EPostaService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    @WebEndpoint(name = "EPostaPort")
    public EPosta getEPostaPort() {
        return super.getPort(EPostaPort, EPosta.class);
    }

    @WebEndpoint(name = "EPostaPort")
    public EPosta getEPostaPort(WebServiceFeature... features) {
        return super.getPort(EPostaPort, EPosta.class, features);
    }
}
