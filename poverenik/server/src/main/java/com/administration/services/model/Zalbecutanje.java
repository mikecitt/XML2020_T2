//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.06 at 12:18:48 AM CET 
//


package com.administration.services.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://localhost:8080/zalba}zalbacutanje" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zalbacutanje"
})
@XmlRootElement(name = "zalbecutanje", namespace = "http://localhost:8080/zalba")
public class Zalbecutanje {

    @XmlElement(namespace = "http://localhost:8080/zalba")
    protected List<Zalbacutanje> zalbacutanje;

    /**
     * Gets the value of the zalbacutanje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zalbacutanje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZalbacutanje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Zalbacutanje }
     * 
     * 
     */
    public List<Zalbacutanje> getZalbacutanje() {
        if (zalbacutanje == null) {
            zalbacutanje = new ArrayList<Zalbacutanje>();
        }
        return this.zalbacutanje;
    }

}
