package com.administration.services.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accessToken",
        "expiresIn",
        "role"
})
@XmlRootElement(name = "token")
public class UserTokenStateDTO {
    @XmlElement(name = "accessToken", required = true)
    private String accessToken;
    @XmlElement(name = "expiresIn", required = true)
    private Long expiresIn;
    @XmlElement(name = "role", required = true)
    private String role;

    public UserTokenStateDTO() {
        this.accessToken = null;
        this.expiresIn = null;
        this.role = null;
    }

    public UserTokenStateDTO(String accessToken, long expiresIn, String role) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
