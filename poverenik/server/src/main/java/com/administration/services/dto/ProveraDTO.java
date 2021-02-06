package com.administration.services.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "dozvola"
})
@XmlRootElement(name = "provera")
public class ProveraDTO {
    @XmlElement(name = "dozvola", required = true)
    private boolean dozvola;

    public ProveraDTO() {
    }


    public ProveraDTO(boolean dozvola) {
        this.dozvola = dozvola;
    }

    public boolean isDozvola() {
        return dozvola;
    }

    public void setDozvola(boolean dozvola) {
        this.dozvola = dozvola;
    }
}
