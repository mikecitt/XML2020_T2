//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.06 at 10:52:21 AM CET 
//


package com.administration.services.model;

import com.administration.services.configs.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;


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
 *         &lt;element name="podnosilac"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attGroup ref="{http://localhost:8080/tipovi}rdfAttributesResource"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="datum_podnosenja"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://localhost:8080/tipovi&gt;Tdatum"&gt;
 *                 &lt;attGroup ref="{http://localhost:8080/tipovi}rdfAttributes"/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zalbe"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="na_odluku"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cutanje"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="spisak"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="zalba" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="zahtevi"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attGroup ref="{http://localhost:8080/tipovi}rdfAttributesResource"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipIzvestaj", propOrder = {
    "podnosilac",
    "datumPodnosenja",
    "zalbe",
    "zahtevi"
})
@XmlRootElement(name = "izvestaj", namespace = "http://localhost:8080/izvestaj")
public class Izvestaj {

    @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
    protected Podnosilac podnosilac;
    @XmlElement(name = "datum_podnosenja", namespace = "http://localhost:8080/izvestaj", required = true)
    protected DatumPodnosenja datumPodnosenja;
    @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
    protected Zalbe zalbe;
    @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
    protected Zahtevi zahtevi;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    protected String about;
    @XmlAttribute(name = "rel")
    @XmlSchemaType(name = "anySimpleType")
    protected String rel;
    @XmlAttribute(name = "href")
    @XmlSchemaType(name = "anySimpleType")
    protected String href;
    @XmlAttribute(name = "vocab")
    @XmlSchemaType(name = "anySimpleType")
    protected String vocab;

    /**
     * Gets the value of the podnosilac property.
     *
     * @return
     *     possible object is
     *     {@link Podnosilac }
     *
     */
    public Podnosilac getPodnosilac() {
        return podnosilac;
    }

    /**
     * Sets the value of the podnosilac property.
     *
     * @param value
     *     allowed object is
     *     {@link Podnosilac }
     *
     */
    public void setPodnosilac(Podnosilac value) {
        this.podnosilac = value;
    }

    /**
     * Gets the value of the datumPodnosenja property.
     *
     * @return
     *     possible object is
     *     {@link DatumPodnosenja }
     *
     */
    public DatumPodnosenja getDatumPodnosenja() {
        return datumPodnosenja;
    }

    /**
     * Sets the value of the datumPodnosenja property.
     *
     * @param value
     *     allowed object is
     *     {@link DatumPodnosenja }
     *
     */
    public void setDatumPodnosenja(DatumPodnosenja value) {
        this.datumPodnosenja = value;
    }

    /**
     * Gets the value of the zalbe property.
     *
     * @return
     *     possible object is
     *     {@link Zalbe }
     *
     */
    public Zalbe getZalbe() {
        return zalbe;
    }

    /**
     * Sets the value of the zalbe property.
     *
     * @param value
     *     allowed object is
     *     {@link Zalbe }
     *
     */
    public void setZalbe(Zalbe value) {
        this.zalbe = value;
    }

    /**
     * Gets the value of the zahtevi property.
     *
     * @return
     *     possible object is
     *     {@link Zahtevi }
     *
     */
    public Zahtevi getZahtevi() {
        return zahtevi;
    }

    /**
     * Sets the value of the zahtevi property.
     *
     * @param value
     *     allowed object is
     *     {@link Zahtevi }
     *
     */
    public void setZahtevi(Zahtevi value) {
        this.zahtevi = value;
    }

    /**
     * Gets the value of the about property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Gets the value of the rel property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets the value of the rel property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRel(String value) {
        this.rel = value;
    }

    /**
     * Gets the value of the href property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the vocab property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVocab() {
        return vocab;
    }

    /**
     * Sets the value of the vocab property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVocab(String value) {
        this.vocab = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://localhost:8080/tipovi&gt;Tdatum"&gt;
     *       &lt;attGroup ref="{http://localhost:8080/tipovi}rdfAttributes"/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class DatumPodnosenja {

        @XmlValue
        @XmlJavaTypeAdapter(DateAdapter.class)
        protected Date value;
        @XmlAttribute(name = "property")
        @XmlSchemaType(name = "anySimpleType")
        protected String property;
        @XmlAttribute(name = "datatype")
        @XmlSchemaType(name = "anySimpleType")
        protected String datatype;

        /**
         * Gets the value of the value property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public Date getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setValue(Date value) {
            this.value = value;
        }

        /**
         * Gets the value of the property property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProperty() {
            return property;
        }

        /**
         * Sets the value of the property property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the datatype property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDatatype() {
            return datatype;
        }

        /**
         * Sets the value of the datatype property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }

    }


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
     *         &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attGroup ref="{http://localhost:8080/tipovi}rdfAttributesResource"/&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ime",
        "prezime"
    })
    public static class Podnosilac {

        @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
        protected String ime;
        @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
        protected String prezime;
        @XmlAttribute(name = "about")
        @XmlSchemaType(name = "anySimpleType")
        protected String about;
        @XmlAttribute(name = "rel")
        @XmlSchemaType(name = "anySimpleType")
        protected String rel;
        @XmlAttribute(name = "href")
        @XmlSchemaType(name = "anySimpleType")
        protected String href;
        @XmlAttribute(name = "vocab")
        @XmlSchemaType(name = "anySimpleType")
        protected String vocab;

        /**
         * Gets the value of the ime property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIme() {
            return ime;
        }

        /**
         * Sets the value of the ime property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIme(String value) {
            this.ime = value;
        }

        /**
         * Gets the value of the prezime property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPrezime() {
            return prezime;
        }

        /**
         * Sets the value of the prezime property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPrezime(String value) {
            this.prezime = value;
        }

        /**
         * Gets the value of the about property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAbout() {
            return about;
        }

        /**
         * Sets the value of the about property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAbout(String value) {
            this.about = value;
        }

        /**
         * Gets the value of the rel property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getRel() {
            return rel;
        }

        /**
         * Sets the value of the rel property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setRel(String value) {
            this.rel = value;
        }

        /**
         * Gets the value of the href property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getHref() {
            return href;
        }

        /**
         * Sets the value of the href property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setHref(String value) {
            this.href = value;
        }

        /**
         * Gets the value of the vocab property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVocab() {
            return vocab;
        }

        /**
         * Sets the value of the vocab property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVocab(String value) {
            this.vocab = value;
        }

    }


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
     *         &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
        "brojPrihvacenih",
        "brojPodnetih",
        "brojOdbijenih"
    })
    public static class Zahtevi {

        @XmlElement(name = "broj_prihvacenih", namespace = "http://localhost:8080/izvestaj")
        protected int brojPrihvacenih;
        @XmlElement(name = "broj_podnetih", namespace = "http://localhost:8080/izvestaj")
        protected int brojPodnetih;
        @XmlElement(name = "broj_odbijenih", namespace = "http://localhost:8080/izvestaj")
        protected int brojOdbijenih;

        /**
         * Gets the value of the brojPrihvacenih property.
         *
         */
        public int getBrojPrihvacenih() {
            return brojPrihvacenih;
        }

        /**
         * Sets the value of the brojPrihvacenih property.
         *
         */
        public void setBrojPrihvacenih(int value) {
            this.brojPrihvacenih = value;
        }

        /**
         * Gets the value of the brojPodnetih property.
         *
         */
        public int getBrojPodnetih() {
            return brojPodnetih;
        }

        /**
         * Sets the value of the brojPodnetih property.
         *
         */
        public void setBrojPodnetih(int value) {
            this.brojPodnetih = value;
        }

        /**
         * Gets the value of the brojOdbijenih property.
         *
         */
        public int getBrojOdbijenih() {
            return brojOdbijenih;
        }

        /**
         * Sets the value of the brojOdbijenih property.
         *
         */
        public void setBrojOdbijenih(int value) {
            this.brojOdbijenih = value;
        }

    }


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
     *         &lt;element name="na_odluku"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cutanje"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="spisak"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="zalba" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
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
        "naOdluku",
        "cutanje",
        "spisak"
    })
    public static class Zalbe {

        @XmlElement(name = "na_odluku", namespace = "http://localhost:8080/izvestaj", required = true)
        protected NaOdluku naOdluku;
        @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
        protected Cutanje cutanje;
        @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
        protected Spisak spisak;

        /**
         * Gets the value of the naOdluku property.
         *
         * @return
         *     possible object is
         *     {@link NaOdluku }
         *
         */
        public NaOdluku getNaOdluku() {
            return naOdluku;
        }

        /**
         * Sets the value of the naOdluku property.
         *
         * @param value
         *     allowed object is
         *     {@link NaOdluku }
         *
         */
        public void setNaOdluku(NaOdluku value) {
            this.naOdluku = value;
        }

        /**
         * Gets the value of the cutanje property.
         *
         * @return
         *     possible object is
         *     {@link Cutanje }
         *
         */
        public Cutanje getCutanje() {
            return cutanje;
        }

        /**
         * Sets the value of the cutanje property.
         *
         * @param value
         *     allowed object is
         *     {@link Cutanje }
         *
         */
        public void setCutanje(Cutanje value) {
            this.cutanje = value;
        }

        /**
         * Gets the value of the spisak property.
         *
         * @return
         *     possible object is
         *     {@link Spisak }
         *
         */
        public Spisak getSpisak() {
            return spisak;
        }

        /**
         * Sets the value of the spisak property.
         *
         * @param value
         *     allowed object is
         *     {@link Spisak }
         *
         */
        public void setSpisak(Spisak value) {
            this.spisak = value;
        }


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
         *         &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
            "brojPrihvacenih",
            "brojPodnetih",
            "brojOdbijenih"
        })
        public static class Cutanje {

            @XmlElement(name = "broj_prihvacenih", namespace = "http://localhost:8080/izvestaj")
            protected int brojPrihvacenih;
            @XmlElement(name = "broj_podnetih", namespace = "http://localhost:8080/izvestaj")
            protected int brojPodnetih;
            @XmlElement(name = "broj_odbijenih", namespace = "http://localhost:8080/izvestaj")
            protected int brojOdbijenih;

            /**
             * Gets the value of the brojPrihvacenih property.
             * 
             */
            public int getBrojPrihvacenih() {
                return brojPrihvacenih;
            }

            /**
             * Sets the value of the brojPrihvacenih property.
             * 
             */
            public void setBrojPrihvacenih(int value) {
                this.brojPrihvacenih = value;
            }

            /**
             * Gets the value of the brojPodnetih property.
             * 
             */
            public int getBrojPodnetih() {
                return brojPodnetih;
            }

            /**
             * Sets the value of the brojPodnetih property.
             * 
             */
            public void setBrojPodnetih(int value) {
                this.brojPodnetih = value;
            }

            /**
             * Gets the value of the brojOdbijenih property.
             * 
             */
            public int getBrojOdbijenih() {
                return brojOdbijenih;
            }

            /**
             * Sets the value of the brojOdbijenih property.
             * 
             */
            public void setBrojOdbijenih(int value) {
                this.brojOdbijenih = value;
            }

        }


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
         *         &lt;element name="broj_prihvacenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="broj_podnetih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="broj_odbijenih" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
            "brojPrihvacenih",
            "brojPodnetih",
            "brojOdbijenih"
        })
        public static class NaOdluku {

            @XmlElement(name = "broj_prihvacenih", namespace = "http://localhost:8080/izvestaj")
            protected int brojPrihvacenih;
            @XmlElement(name = "broj_podnetih", namespace = "http://localhost:8080/izvestaj")
            protected int brojPodnetih;
            @XmlElement(name = "broj_odbijenih", namespace = "http://localhost:8080/izvestaj")
            protected int brojOdbijenih;

            /**
             * Gets the value of the brojPrihvacenih property.
             * 
             */
            public int getBrojPrihvacenih() {
                return brojPrihvacenih;
            }

            /**
             * Sets the value of the brojPrihvacenih property.
             * 
             */
            public void setBrojPrihvacenih(int value) {
                this.brojPrihvacenih = value;
            }

            /**
             * Gets the value of the brojPodnetih property.
             * 
             */
            public int getBrojPodnetih() {
                return brojPodnetih;
            }

            /**
             * Sets the value of the brojPodnetih property.
             * 
             */
            public void setBrojPodnetih(int value) {
                this.brojPodnetih = value;
            }

            /**
             * Gets the value of the brojOdbijenih property.
             * 
             */
            public int getBrojOdbijenih() {
                return brojOdbijenih;
            }

            /**
             * Sets the value of the brojOdbijenih property.
             * 
             */
            public void setBrojOdbijenih(int value) {
                this.brojOdbijenih = value;
            }

        }


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
         *         &lt;element name="zalba" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
            "zalba"
        })
        public static class Spisak {

            @XmlElement(namespace = "http://localhost:8080/izvestaj", required = true)
            protected String zalba;

            /**
             * Gets the value of the zalba property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZalba() {
                return zalba;
            }

            /**
             * Sets the value of the zalba property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZalba(String value) {
                this.zalba = value;
            }

        }

    }

}