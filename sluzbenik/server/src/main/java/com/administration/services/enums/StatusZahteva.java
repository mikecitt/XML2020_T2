package com.administration.services.enums;

public enum StatusZahteva {
    ODBIJEN("ODBIJEN"), OBRADA("OBRADA"), PRIHVACEN("PRIHVACEN");

    private final String text;

    StatusZahteva(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
