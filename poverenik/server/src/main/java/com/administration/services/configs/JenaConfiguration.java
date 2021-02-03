package com.administration.services.configs;

import com.administration.services.helpers.MetadataExtractor;
import com.administration.services.helpers.SparqlUtil;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

    public void updateRDF(String xml) throws Exception {
        String SPARQL_NAMED_GRAPH_URI = "/administration/metadata";

        InputStream input = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        MetadataExtractor metadataExtractor = new MetadataExtractor();
        metadataExtractor.extractMetadata(input, output);

        String rdf = new String(output.toByteArray(), StandardCharsets.UTF_8);
        Model model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(rdf.getBytes(StandardCharsets.UTF_8)), SparqlUtil.NTRIPLES);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);

        // Writing the named graph
        String sparqlUpdate = SparqlUtil.insertData(dataEndpoint + SPARQL_NAMED_GRAPH_URI,
                new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, updateEndpoint);
        processor.execute();

        // Read the triples from the named graph
        System.out.println();
        System.out.println("[INFO] Retrieving triples from RDF store.");
        System.out.println("[INFO] Using \"" + SPARQL_NAMED_GRAPH_URI + "\" named graph.");

        System.out.println("[INFO] Selecting the triples from the named graph \"" + SPARQL_NAMED_GRAPH_URI + "\".");
        String sparqlQuery = SparqlUtil.selectData(dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");

        QueryExecution query = QueryExecutionFactory.sparqlService(queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close();
    }
}
