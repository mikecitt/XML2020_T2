package com.administration.services.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExistConfiguration {

    @Value("${exist.datasource.user:admin}")
    public String user;

    @Value("${exist.datasource.password:}")
    public String password;

    @Value("${exist.driver:org.exist.xmldb.DatabaseImpl}")
    public String driver;

    @Value("xmldb:exist://${exist.datasource.server-name:localhost}:${exist.datasource.port:8088}/exist/xmlrpc")
    public String uri;
}
