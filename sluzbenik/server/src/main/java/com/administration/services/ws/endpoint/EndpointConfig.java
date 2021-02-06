package com.administration.services.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.administration.services.ws.resenje.ResenjePortImpl;
import com.administration.services.ws.zalbe.ZalbaPortImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint zalbaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ZalbaPortImpl());
        endpoint.publish("/zalbaOdgovor");
        return endpoint;
    }

    @Bean
    public Endpoint resenjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ResenjePortImpl());
        endpoint.publish("/resenje");
        return endpoint;
    }
}
