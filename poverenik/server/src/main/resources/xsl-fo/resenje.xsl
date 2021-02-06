<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:res="http://localhost:8080/resenje"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin-left="2cm" margin-top="2cm" margin-right="2cm" margin-bottom="2.54cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow font-family="Times New Roman" text-align="justify" font-size="12px" flow-name="xsl-region-body">
                    <fo:block>
                        <fo:leader leader-pattern="rule" color="gray" leader-length="100%" rule-style="solid" rule-thickness="2pt"/>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Решење - </xsl:text>
                        <xsl:value-of select="//res:rezultat_resenja"/>
                    </fo:block>
                    <fo:block text-align-last="justify">
                        <xsl:text>Бр. </xsl:text>
                        <xsl:value-of select="//res:broj_predmeta"/>
                        <xsl:text>/</xsl:text>
                        <xsl:value-of select="//res:datum_predmeta"/>
                        <fo:leader leader-pattern="space" />
                        <xsl:text>Датум: </xsl:text>
                        <xsl:value-of select="format-date(//res:datum_resenja, '[D01].[M01].[Y0001].')"/>
                        <xsl:text> године.</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Повереник за информације од јавног значаја и заштиту података о личности, у поступку по жалби
                        коју је изјавио </xsl:text>
                        <xsl:value-of select="substring(//res:podnositelj_zalbe/res:ime, 1, 1)"/>
                        <xsl:value-of select="substring(//res:podnositelj_zalbe/res:prezime, 1, 1)"/>
                        <xsl:text>, </xsl:text>
                        <xsl:value-of select="//res:razlog"/>
                        <xsl:text>,  доноси</xsl:text>
                    </fo:block>
                    <fo:block text-align="center">
                        <xsl:text>Р Е Ш Е Њ Е</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:value-of select="//res:odgovor_na_zalbu"/>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="center">
                        <xsl:text>О б р а з л о ж е њ е</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:value-of select="//res:uvod_obrazlozenja"/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Поступајући по жалби, </xsl:text>
                        <xsl:value-of select="//res:postupajuci_po_zalbi"/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>По разматрању жалбе и осталих списа овог предмета, донета је одлука као у диспозитиву
                        решења из следећих разлога:</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Увидом у списе предмета утврђено је да је </xsl:text>
                        <xsl:value-of select="//res:uvid"/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Имајући у виду </xsl:text>
                        <xsl:value-of select="//res:dodatan_uvid"/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:value-of select="//res:odgovor_poverenika"/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Упутство о правном средству:</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Против овог решења није допуштена жалба већ се, у складу са Законом о управним
                        споровима, може покренути управни спор тужбом Управном суду у </xsl:text>
                        <xsl:value-of select="//res:upravni_spor/res:mesto"/>
                        <xsl:text>, у року од </xsl:text>
                        <xsl:value-of select="//res:upravni_spor/res:rok_prijema"/>
                        <xsl:text> дана од
                        дана пријема решења. Такса на тужбу износи </xsl:text>
                        <xsl:value-of select="//res:upravni_spor/res:taksa"/>
                        <xsl:text> динара.</xsl:text>
                    </fo:block>
                    <fo:block text-align="right">
                        <xsl:text>ПОВЕРЕНИК</xsl:text>
                    </fo:block>
                    <fo:block text-align="right">
                        <xsl:value-of select="//res:poverenik/res:ime"/>
                        <xsl:text>&#160;</xsl:text>
                        <xsl:value-of select="//res:poverenik/res:prezime"/>
                    </fo:block>
                    <fo:block>
                        <fo:leader leader-pattern="rule" color="gray" leader-length="100%" rule-style="solid" rule-thickness="2pt"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
