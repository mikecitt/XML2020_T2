package com.administration.services.helpers;

import java.util.HashMap;
import java.util.Map;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 * Implementation of {@link NamespacePrefixMapper} that maps the schema
 * namespaces more to readable names. Used by the jaxb marshaller. Requires
 * setting the property "com.sun.xml.bind.namespacePrefixMapper" to an instance
 * of this class.
 * <p>
 * Requires dependency on JAXB implementation jars
 * </p>
 */
public class DefaultNamespacePrefixMapper extends NamespacePrefixMapper {

    private Map<String, String> namespaceMap = new HashMap<>();

    /**
     * Create mappings.
     */
    public DefaultNamespacePrefixMapper() {
        namespaceMap.put("http://localhost:8080/obavestenje", "obv");
        namespaceMap.put("http://localhost:8080/korisnici", "kor");
        namespaceMap.put("http://localhost:8080/resenje", "res");
        namespaceMap.put("http://localhost:8080/tipovi", "tip");
        namespaceMap.put("http://localhost:8080/zahtevcir", "zah");
        namespaceMap.put("http://localhost:8080/zalba", "zal");
        namespaceMap.put("http://localhost:8080/zalbanaodluku", "zlbod");
        namespaceMap.put("http://www.w3.org/2001/XMLSchema#", "xs");
        namespaceMap.put("http://www.w3.org/ns/rdfa#", "");
        namespaceMap.put("http://localhost:8080/rdf/predicate/", "pred");
    }

    /*
     * (non-Javadoc) Returning null when not found based on spec.
     * 
     * @see
     * com.sun.xml.bind.marshaller.NamespacePrefixMapper#getPreferredPrefix(java.
     * lang.String, java.lang.String, boolean)
     */
    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return namespaceMap.getOrDefault(namespaceUri, suggestion);
    }
}