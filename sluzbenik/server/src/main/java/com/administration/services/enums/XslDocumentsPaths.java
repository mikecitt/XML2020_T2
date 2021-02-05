package com.administration.services.enums;

public enum XslDocumentsPaths {
    ZAHTEV("src/main/resources/xsl-fo/zahtevcir.xsl"), OBAVESTENJE("src/main/resources/xsl-fo/obavestenjecir.xsl");

    private final String text;

    XslDocumentsPaths(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
