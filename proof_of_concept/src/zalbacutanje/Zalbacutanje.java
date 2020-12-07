//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 05:27:36 PM CET 
//


package zalbacutanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="naziv_organa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="razlog_zalbe"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *               &lt;minInclusive value="1"/&gt;
 *               &lt;maxInclusive value="3"/&gt;
 *               &lt;pattern value="[1-3]"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="datum_zahteva" type="{http://localhost:8080/zalba}dateNullable"/&gt;
 *         &lt;element name="podaci_zahteva" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="detalji_predaje"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="mesto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="datum" type="{http://localhost:8080/zalba}dateNullable"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="informacije_o_podnosiocu_zalbe"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="drugi_kontakt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "nazivOrgana",
    "razlogZalbe",
    "datumZahteva",
    "podaciZahteva",
    "detaljiPredaje",
    "informacijeOPodnosiocuZalbe"
})
@XmlRootElement(name = "zalbacutanje")
public class Zalbacutanje {

    @XmlElement(name = "naziv_organa", required = true)
    protected String nazivOrgana;
    @XmlElement(name = "razlog_zalbe")
    protected int razlogZalbe;
    @XmlElement(name = "datum_zahteva", required = true)
    protected String datumZahteva;
    @XmlElement(name = "podaci_zahteva", required = true)
    protected String podaciZahteva;
    @XmlElement(name = "detalji_predaje", required = true)
    protected Zalbacutanje.DetaljiPredaje detaljiPredaje;
    @XmlElement(name = "informacije_o_podnosiocu_zalbe", required = true)
    protected Zalbacutanje.InformacijeOPodnosiocuZalbe informacijeOPodnosiocuZalbe;

    /**
     * Gets the value of the nazivOrgana property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivOrgana() {
        return nazivOrgana;
    }

    /**
     * Sets the value of the nazivOrgana property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivOrgana(String value) {
        this.nazivOrgana = value;
    }

    /**
     * Gets the value of the razlogZalbe property.
     * 
     */
    public int getRazlogZalbe() {
        return razlogZalbe;
    }

    /**
     * Sets the value of the razlogZalbe property.
     * 
     */
    public void setRazlogZalbe(int value) {
        this.razlogZalbe = value;
    }

    /**
     * Gets the value of the datumZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumZahteva() {
        return datumZahteva;
    }

    /**
     * Sets the value of the datumZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumZahteva(String value) {
        this.datumZahteva = value;
    }

    /**
     * Gets the value of the podaciZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodaciZahteva() {
        return podaciZahteva;
    }

    /**
     * Sets the value of the podaciZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodaciZahteva(String value) {
        this.podaciZahteva = value;
    }

    /**
     * Gets the value of the detaljiPredaje property.
     * 
     * @return
     *     possible object is
     *     {@link Zalbacutanje.DetaljiPredaje }
     *     
     */
    public Zalbacutanje.DetaljiPredaje getDetaljiPredaje() {
        return detaljiPredaje;
    }

    /**
     * Sets the value of the detaljiPredaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalbacutanje.DetaljiPredaje }
     *     
     */
    public void setDetaljiPredaje(Zalbacutanje.DetaljiPredaje value) {
        this.detaljiPredaje = value;
    }

    /**
     * Gets the value of the informacijeOPodnosiocuZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Zalbacutanje.InformacijeOPodnosiocuZalbe }
     *     
     */
    public Zalbacutanje.InformacijeOPodnosiocuZalbe getInformacijeOPodnosiocuZalbe() {
        return informacijeOPodnosiocuZalbe;
    }

    /**
     * Sets the value of the informacijeOPodnosiocuZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalbacutanje.InformacijeOPodnosiocuZalbe }
     *     
     */
    public void setInformacijeOPodnosiocuZalbe(Zalbacutanje.InformacijeOPodnosiocuZalbe value) {
        this.informacijeOPodnosiocuZalbe = value;
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
     *         &lt;element name="mesto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="datum" type="{http://localhost:8080/zalba}dateNullable"/&gt;
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
        "mesto",
        "datum"
    })
    public static class DetaljiPredaje {

        @XmlElement(required = true)
        protected String mesto;
        @XmlElement(required = true)
        protected String datum;

        /**
         * Gets the value of the mesto property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMesto() {
            return mesto;
        }

        /**
         * Sets the value of the mesto property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMesto(String value) {
            this.mesto = value;
        }

        /**
         * Gets the value of the datum property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatum() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatum(String value) {
            this.datum = value;
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
     *         &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="drugi_kontakt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "ime",
        "prezime",
        "adresa",
        "drugiKontakt"
    })
    public static class InformacijeOPodnosiocuZalbe {

        @XmlElement(required = true)
        protected String ime;
        @XmlElement(required = true)
        protected String prezime;
        @XmlElement(required = true)
        protected String adresa;
        @XmlElement(name = "drugi_kontakt", required = true)
        protected String drugiKontakt;

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
         * Gets the value of the adresa property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdresa() {
            return adresa;
        }

        /**
         * Sets the value of the adresa property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdresa(String value) {
            this.adresa = value;
        }

        /**
         * Gets the value of the drugiKontakt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDrugiKontakt() {
            return drugiKontakt;
        }

        /**
         * Sets the value of the drugiKontakt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDrugiKontakt(String value) {
            this.drugiKontakt = value;
        }

    }

}