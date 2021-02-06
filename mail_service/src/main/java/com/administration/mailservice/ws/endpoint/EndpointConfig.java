package com.administration.mailservice.ws.endpoint;

import com.administration.mailservice.ws.eposta.EPosta;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private EPosta ePostaPort;

    @Bean
    public Endpoint ePostaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, ePostaPort);
        endpoint.publish("/eposta");
        return endpoint;
    }
}
