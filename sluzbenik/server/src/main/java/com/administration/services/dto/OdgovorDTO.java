package com.administration.services.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipOdgovor", propOrder = { "tekst"})
@XmlRootElement(name = "odgovor")
public class OdgovorDTO {
    @XmlElement(name = "tekst", required = true)
    private String tekst;

    public OdgovorDTO() {
        this.tekst = null;
    }

    public OdgovorDTO(String odgovor) {
        this.tekst = odgovor;
    }

    public String getOdgovor() {
        return tekst;
    }

    public void setOdgovor(String tekst) {
        this.tekst = tekst;
    }
}
