package com.administration.services.enums;

public enum TipKorisnika {
    GRADJANIN("ROLE_GRADJANIN"),
    POVERENIK("ROLE_POVERENIK");

    private final String text;

    TipKorisnika(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
