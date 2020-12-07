//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.07 at 04:51:02 PM CET 
//


package zalbanaodluku;

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
 *         &lt;element name="podnosilac_zalbe"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="sediste" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="naziv_organa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="resenje"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="broj_resenja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="datum_resenja" type="{http://localhost:8080/zalbanaodluku}dateNullable"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="datum_podnosenja_zahteva" type="{http://localhost:8080/zalbanaodluku}dateNullable"/&gt;
 *         &lt;element name="opis_zalbe" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="detalji_predaje"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="mesto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="datum" type="{http://localhost:8080/zalbanaodluku}dateNullable"/&gt;
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
    "podnosilacZalbe",
    "nazivOrgana",
    "resenje",
    "datumPodnosenjaZahteva",
    "opisZalbe",
    "detaljiPredaje",
    "informacijeOPodnosiocuZalbe"
})
@XmlRootElement(name = "zalbanaodluku")
public class Zalbanaodluku {

    @XmlElement(name = "podnosilac_zalbe", required = true)
    protected Zalbanaodluku.PodnosilacZalbe podnosilacZalbe;
    @XmlElement(name = "naziv_organa", required = true)
    protected String nazivOrgana;
    @XmlElement(required = true)
    protected Zalbanaodluku.Resenje resenje;
    @XmlElement(name = "datum_podnosenja_zahteva", required = true)
    protected String datumPodnosenjaZahteva;
    @XmlElement(name = "opis_zalbe", required = true)
    protected String opisZalbe;
    @XmlElement(name = "detalji_predaje", required = true)
    protected Zalbanaodluku.DetaljiPredaje detaljiPredaje;
    @XmlElement(name = "informacije_o_podnosiocu_zalbe", required = true)
    protected Zalbanaodluku.InformacijeOPodnosiocuZalbe informacijeOPodnosiocuZalbe;

    /**
     * Gets the value of the podnosilacZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Zalbanaodluku.PodnosilacZalbe }
     *     
     */
    public Zalbanaodluku.PodnosilacZalbe getPodnosilacZalbe() {
        return podnosilacZalbe;
    }

    /**
     * Sets the value of the podnosilacZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalbanaodluku.PodnosilacZalbe }
     *     
     */
    public void setPodnosilacZalbe(Zalbanaodluku.PodnosilacZalbe value) {
        this.podnosilacZalbe = value;
    }

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
     * Gets the value of the resenje property.
     * 
     * @return
     *     possible object is
     *     {@link Zalbanaodluku.Resenje }
     *     
     */
    public Zalbanaodluku.Resenje getResenje() {
        return resenje;
    }

    /**
     * Sets the value of the resenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalbanaodluku.Resenje }
     *     
     */
    public void setResenje(Zalbanaodluku.Resenje value) {
        this.resenje = value;
    }

    /**
     * Gets the value of the datumPodnosenjaZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumPodnosenjaZahteva() {
        return datumPodnosenjaZahteva;
    }

    /**
     * Sets the value of the datumPodnosenjaZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumPodnosenjaZahteva(String value) {
        this.datumPodnosenjaZahteva = value;
    }

    /**
     * Gets the value of the opisZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpisZalbe() {
        return opisZalbe;
    }

    /**
     * Sets the value of the opisZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpisZalbe(String value) {
        this.opisZalbe = value;
    }

    /**
     * Gets the value of the detaljiPredaje property.
     * 
     * @return
     *     possible object is
     *     {@link Zalbanaodluku.DetaljiPredaje }
     *     
     */
    public Zalbanaodluku.DetaljiPredaje getDetaljiPredaje() {
        return detaljiPredaje;
    }

    /**
     * Sets the value of the detaljiPredaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalbanaodluku.DetaljiPredaje }
     *     
     */
    public void setDetaljiPredaje(Zalbanaodluku.DetaljiPredaje value) {
        this.detaljiPredaje = value;
    }

    /**
     * Gets the value of the informacijeOPodnosiocuZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Zalbanaodluku.InformacijeOPodnosiocuZalbe }
     *     
     */
    public Zalbanaodluku.InformacijeOPodnosiocuZalbe getInformacijeOPodnosiocuZalbe() {
        return informacijeOPodnosiocuZalbe;
    }

    /**
     * Sets the value of the informacijeOPodnosiocuZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zalbanaodluku.InformacijeOPodnosiocuZalbe }
     *     
     */
    public void setInformacijeOPodnosiocuZalbe(Zalbanaodluku.InformacijeOPodnosiocuZalbe value) {
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
     *         &lt;element name="datum" type="{http://localhost:8080/zalbanaodluku}dateNullable"/&gt;
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
     *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="sediste" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "naziv",
        "adresa",
        "sediste"
    })
    public static class PodnosilacZalbe {

        @XmlElement(required = true)
        protected String ime;
        @XmlElement(required = true)
        protected String prezime;
        @XmlElement(required = true)
        protected String naziv;
        @XmlElement(required = true)
        protected String adresa;
        @XmlElement(required = true)
        protected String sediste;

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
         * Gets the value of the naziv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaziv() {
            return naziv;
        }

        /**
         * Sets the value of the naziv property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaziv(String value) {
            this.naziv = value;
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
         * Gets the value of the sediste property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSediste() {
            return sediste;
        }

        /**
         * Sets the value of the sediste property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSediste(String value) {
            this.sediste = value;
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
     *         &lt;element name="broj_resenja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="datum_resenja" type="{http://localhost:8080/zalbanaodluku}dateNullable"/&gt;
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
        "brojResenja",
        "datumResenja"
    })
    public static class Resenje {

        @XmlElement(name = "broj_resenja", required = true)
        protected String brojResenja;
        @XmlElement(name = "datum_resenja", required = true)
        protected String datumResenja;

        /**
         * Gets the value of the brojResenja property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBrojResenja() {
            return brojResenja;
        }

        /**
         * Sets the value of the brojResenja property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBrojResenja(String value) {
            this.brojResenja = value;
        }

        /**
         * Gets the value of the datumResenja property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatumResenja() {
            return datumResenja;
        }

        /**
         * Sets the value of the datumResenja property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatumResenja(String value) {
            this.datumResenja = value;
        }

    }

}