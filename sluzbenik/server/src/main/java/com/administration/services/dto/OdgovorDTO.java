package com.administration.services.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "odgovor"})
@XmlRootElement(name = "odgovor")
public class OdgovorDTO {
    @XmlElement(name = "odgovor", required = true)
    private String odgovor;

    public OdgovorDTO() {
        this.odgovor = null;
    }

    public OdgovorDTO(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }
}