package com.administration.services.ws.endpoint;

import javax.xml.ws.Endpoint;

import com.administration.services.ws.izvestaj.IzvestajPortImpl;
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

    @Autowired
    private ZalbaPortImpl zalbaPort;

    @Autowired
    private IzvestajPortImpl izvestajPort;

    @Bean
    public Endpoint zalbaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zalbaPort);
        endpoint.publish("/zalbaOdgovor");
        return endpoint;
    }

    @Bean
    public Endpoint izvestajEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, izvestajPort);
        endpoint.publish("/izvestaj");
        return endpoint;
    }
}
