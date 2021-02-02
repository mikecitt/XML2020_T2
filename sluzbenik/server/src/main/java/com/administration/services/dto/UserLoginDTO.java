package com.administration.services.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "emailAdresa",
        "sifra"
})
@XmlRootElement(name = "user_login_dto", namespace = "http://localhost:8080/korisnici")
public class UserLoginDTO {
    @XmlElement(name = "email_adresa", namespace = "http://localhost:8080/korisnici", required = true)
    private String emailAdresa;
    @XmlElement(name = "sifra", namespace = "http://localhost:8080/korisnici", required = true)
    private String sifra;

    public UserLoginDTO() {}

    public UserLoginDTO(String emailAdresa, String sifra) {
        this.emailAdresa = emailAdresa;
        this.sifra = sifra;
    }

    public String getUsername() {
        return emailAdresa;
    }

    public void setUsername(String emailAdresa) {
        this.emailAdresa = emailAdresa;
    }

    public String getPassword() {
        return sifra;
    }

    public void setPassword(String sifra) {
        this.sifra = sifra;
    }
}
