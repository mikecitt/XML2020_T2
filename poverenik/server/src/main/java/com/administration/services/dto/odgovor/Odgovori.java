package com.administration.services.dto.odgovor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "odgovorSluzbenika"
})
@XmlRootElement(name = "odgovori", namespace = "http://localhost:8080/odgovori")
public class Odgovori {
    @XmlElement(namespace = "http://localhost:8080/odgovori")
    protected List<OdgovorSluzbenika> odgovorSluzbenika;


    public List<OdgovorSluzbenika> getOdgovori() {
        if (odgovorSluzbenika == null) {
            odgovorSluzbenika = new ArrayList<OdgovorSluzbenika>();
        }
        return this.odgovorSluzbenika;
    }
}
