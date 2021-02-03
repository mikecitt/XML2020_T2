package com.administration.services.enums;

public enum TipKorisnika {
    GRADJANIN("GRADJANIN"),
    POVERENIK("POVERENIK");

    private final String text;

    TipKorisnika(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
