package com.administration.services.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JenaConfiguration {

    @Value("${jena.datasource.endpoint:http://localhost:3030}")
    public String endpoint;

    @Value("${jena.datasource.dataset:PoverenikDataset}")
    public String dataset;

    @Value("${jena.datasource.query:query}")
    public String queryEndpoint;

    @Value("${jena.datasource.update:update}")
    public String updateEndpoint;

    @Value("${jena.datasource.data:data}")
    public String dataEndpoint;

    @Bean
    public void setUpJena() {
        queryEndpoint = String.join("/", endpoint, dataset, queryEndpoint);
        updateEndpoint = String.join("/", endpoint, dataset, updateEndpoint);
        dataEndpoint = String.join("/", endpoint, dataset, dataEndpoint);

        System.out.println("[INFO] Parsing connection properties:");
        System.out.println("[INFO] Query endpoint: " + queryEndpoint);
        System.out.println("[INFO] Update endpoint: " + updateEndpoint);
        System.out.println("[INFO] Graph store endpoint: " + dataEndpoint);
    }
}
