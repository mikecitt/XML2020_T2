package com.administration.services.enums;

public enum XsltDocumentsPaths {
    ZALBACUTANJE("src/main/resources/xslt/zalbacutanje.xsl"), ZALBAODLUKA("src/main/resources/xslt/zalbaodluka.xsl"),
    RESENJE("src/main/resources/xslt/resenje.xsl");

    private final String text;

    XsltDocumentsPaths(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
