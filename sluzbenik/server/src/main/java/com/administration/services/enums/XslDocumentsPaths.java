package com.administration.services.enums;

public enum XslDocumentsPaths {
    ZAHTEV("src/main/resources/xsl-fo/zahtevcir.xsl"),
    OBAVESTENJE("src/main/resources/xsl-fo/obavestenjecir.xsl"),
    ZALBACUTANJE("src/main/resources/xsl-fo/zalbacutanje.xsl"),
    ZALBAODLUKA("src/main/resources/xsl-fo/zalbaodluka.xsl"),
    RESENJE("src/main/resources/xsl-fo/resenje.xsl");

    private final String text;

    XslDocumentsPaths(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
