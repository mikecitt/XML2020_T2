package com.administration.services.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accessToken",
        "expiresIn"
})
@XmlRootElement(name = "token")
public class UserTokenStateDTO {
    @XmlElement(name = "accessToken", required = true)
    private String accessToken;
    @XmlElement(name = "expiresIn", required = true)
    private Long expiresIn;

    public UserTokenStateDTO() {
        this.accessToken = null;
        this.expiresIn = null;
    }

    public UserTokenStateDTO(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
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
}
