package com.administration.services.dto.odgovor;

import com.administration.services.configs.DateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "odgovorSluzbenika", propOrder = {
    "odgovor",
    "datumZahtevanja"
})
@XmlRootElement(name = "odgovorSluzbenika", namespace = "http://localhost:8080/odgovori")
public class OdgovorSluzbenika {

    @XmlElement(name = "odgovor", required = true)
    private String odgovor;
    @XmlElement(name = "datumZahtevanja", required = true)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date datumZahtevanja;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anySimpleType")
    private String about;
    @XmlAttribute(name = "rel")
    @XmlSchemaType(name = "anySimpleType")
    private String rel;
    @XmlAttribute(name = "href")
    @XmlSchemaType(name = "anySimpleType")
    private String href;
    @XmlAttribute(name = "vocab")
    @XmlSchemaType(name = "anySimpleType")
    private String vocab;
    @XmlAttribute(name = "odgovorio")
    @XmlSchemaType(name = "boolean")
    private boolean odgovorio;

    public boolean isOdgovorio() {
        return odgovorio;
    }

    public void setOdgovorio(boolean odgovorio) {
        this.odgovorio = odgovorio;
    }

    public OdgovorSluzbenika() {
    }

    public OdgovorSluzbenika(String odgovor, Date datumZahtevanja) {
        this.odgovor = odgovor;
        this.datumZahtevanja = datumZahtevanja;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public Date getDatumZahtevanja() {
        return datumZahtevanja;
    }

    public void setDatumZahtevanja(Date datumZahtevanja) {
        this.datumZahtevanja = datumZahtevanja;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }
}
