<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:izv="http://localhost:8080/izvestaj"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin-left="3.17cm" margin-top="2.54cm" margin-right="3.17cm" margin-bottom="2.54cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow font-family="Times New Roman" text-align="left" font-size="12px" flow-name="xsl-region-body">
                    <fo:block font-size="24px" font-weight="bold" text-align="center">
                        И З В Е Ш Т А Ј
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block font-size="14px">
                        <xsl:text>Детаљи о извештају:</xsl:text>

                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Подносилац: </xsl:text>
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="//izv:ime"/>
                            <xsl:text>&#160;</xsl:text>
                            <xsl:value-of select="//izv:prezime"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Закључно са: </xsl:text>
                        <fo:inline font-weight="bold">
                            <xsl:value-of select="format-date(//izv:datum_podnosenja, '[D01].[M01].[Y0001].')"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block>
                        <fo:table margin="20px auto 20px auto" border="1px">
                            <fo:table-column column-width="40%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell background-color="#4caf50" color="white" padding="10px" font-weight="bold">
                                        <fo:block>Тип документа</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#4caf50" color="white" padding="10px" font-weight="bold">
                                        <fo:block>У обради</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#4caf50" color="white" padding="10px" font-weight="bold">
                                        <fo:block>Прихваћено</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#4caf50" color="white" padding="10px" font-weight="bold">
                                        <fo:block>Одбијено</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-weight="bold">Захтев</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:zahtevi/izv:broj_podnetih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:zahtevi/izv:broj_prihvacenih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:zahtevi/izv:broj_odbijenih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-weight="bold">Жалба на ћутање</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:cutanje/izv:broj_podnetih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:cutanje/izv:broj_prihvacenih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:cutanje/izv:broj_odbijenih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-weight="bold">Жалба на одлуку</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:na_odluku/izv:broj_podnetih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:na_odluku/izv:broj_prihvacenih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding="10px">
                                        <fo:block>
                                            <xsl:value-of select="//izv:na_odluku/izv:broj_odbijenih"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <fo:block font-size="18px" font-weight="bold" text-align="center">
                        Ж А Л Б Е
                        </fo:block>
                        <fo:block text-align="justify">
                            <xsl:for-each select="//izv:spisak/izv:zalba">
                                <fo:block>
                                    <fo:leader leader-pattern="rule" color="gray" leader-length="100%" rule-style="solid" rule-thickness="2pt"/>
                                </fo:block>
                                <xsl:text>Жалбу је поднео </xsl:text>
                                <xsl:value-of select="current()"/>
                            </xsl:for-each>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
