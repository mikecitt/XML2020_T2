package com.administration.services.enums;

public enum XsltDocumentsPaths {
    ZAHTEV("src/main/resources/xslt/zahtevcir.xsl"), OBAVESTENJE("src/main/resources/xslt/obavestenjecir.xsl"),;

    private final String text;

    XsltDocumentsPaths(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
