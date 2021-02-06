package com.administration.services.enums;

public enum Status {
    OBRADA("OBRADA"),
    GOTOV("GOTOV"),
    ZAHTEVANJE("ZAHTEVANJE");

    private final String text;

    Status(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
