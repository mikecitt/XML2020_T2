package com.administration.services.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;

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

    @Bean
    public void setUpDatabase() throws Exception {
        System.out.println("[INFO] Loading driver class: " + driver);
        Class<?> cl = Class.forName(driver);


        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);
    }
}
