<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:obv="http://localhost:8080/obavestenje"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin-left="3.17cm" margin-top="2.54cm" margin-right="3.17cm" margin-bottom="2.54cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow font-family="Times New Roman" text-align="left" font-size="11px" flow-name="xsl-region-body">
                    <fo:block>
                        <fo:inline-container width="164px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//obv:naziv_organa"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <fo:inline-container width="164px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//obv:sediste_organa"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block margin-left="2em">
                        <xsl:text>(назив и седиште органа)</xsl:text>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Број предмета: </xsl:text>
                        <fo:inline-container width="92px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//obv:broj_predmeta"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Датум: </xsl:text>
                        <fo:inline padding-left="3.5em">
                            <fo:inline-container width="92px">
                                <fo:block text-align="center" border-bottom="1px dotted">
                                    <xsl:value-of select="format-date(//obv:zaglavlje/obv:datum, '[D01].[M01].[Y0001].')"/>
                                </fo:block>
                            </fo:inline-container>
                        </fo:inline>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <fo:inline-container width="250px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//obv:podnosioc_zahteva/obv:ime"/>
                                <xsl:text>&#160;</xsl:text>
                                <xsl:value-of select="//obv:podnosioc_zahteva/obv:prezime"/>
                                <fo:leader/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <fo:inline-container width="250px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                &#160;
                                <fo:leader/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <fo:inline-container width="250px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//obv:podnosioc_zahteva/obv:adresa"/>
                                <fo:leader/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Име и презиме / назив / и адреса подносиоца захтева</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block font-size="12px" text-align="center" font-weight="bold">
                        <xsl:text>О Б А В Е Ш Т Е Њ Е</xsl:text>
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        <xsl:text>о стављању на увид документа који садржи</xsl:text>
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        <xsl:text>тражену информацију и о изради копије</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>На основу члана 16. ст. 1. Закона о слободном приступу информацијама од јавног значаја, 
                        поступајући по вашем захтеву за слободан приступ информацијама од </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="format-date(//obv:datum_informacija, '[D01].[M01].[Y0001].')"/>
                        </fo:inline>
                        <xsl:text>год., којим сте тражили увид у документ/е са информацијама о / у вези са: </xsl:text>
                    </fo:block>
                    <fo:block text-align="justify">
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:opis_informacije"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-align="center">
                        <xsl:text>(опис тражене информације)</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="justify">
                        <xsl:text>обавештавамо вас да дана </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="format-date(//obv:vreme/obv:datum, '[D01].[M01].[Y0001].')"/>
                        </fo:inline>
                        <xsl:text>, у </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:vreme/obv:cas"/>
                        </fo:inline>
                        <xsl:text> часова, односно у времену од </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:trajanje/obv:pocetak"/>
                        </fo:inline>
                        <xsl:text> до </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:trajanje/obv:kraj"/>
                        </fo:inline>
                        <xsl:text> часова, у просторијама органа у </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:mesto"/>
                        </fo:inline>
                        <xsl:text> ул. </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:ulica"/>
                        </fo:inline>
                        <xsl:text> бр. </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:broj"/>
                        </fo:inline>
                        <xsl:text>, канцеларија бр. </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:br_kancelarije"/>
                        </fo:inline>
                        <xsl:text> можете </xsl:text>
                        <fo:inline font-weight="bold">
                            <xsl:text>извршити увид</xsl:text>
                        </fo:inline>
                        <xsl:text> у документ/е у коме је садржана тражена информација</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Том приликом, на ваш захтев, може вам се издати и копија документа са траженом информацијом</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и то: копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, дискете 20 динара, DVD 40 динара, аудио-касета – 150 динара, видео-касета 300 динара, претварање једне стране документа из физичког у електронски облик – 30 динара.</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        <xsl:text>Износ укупних трошкова израде копије документа по вашем захтеву износи </xsl:text>
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//obv:trosak"/>
                        </fo:inline>
                        <xsl:text> динара и уплаћује се на жиро-рачун Буџета Републике Србије бр. 840-742328-843-30, с позивом на број 97 – ознака шифре општине/града где се налази орган власти (из Правилника о условима и начину вођења рачуна – „Сл. гласник РС“, 20/07... 40/10). </xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <xsl:text>Достављено: </xsl:text>
                    </fo:block>
                    <fo:block>
                        <xsl:text>1.</xsl:text>
                        <fo:leader/>
                        <xsl:text>Именованом</xsl:text>
                        <fo:inline padding-left="5em">(М.П.)</fo:inline>
                    </fo:block>
                    <fo:block>
                        <xsl:text>2.</xsl:text>
                        <fo:leader/>
                        <xsl:text>Архиви</xsl:text>
                    </fo:block>
                    <fo:block text-align="right">
                        <xsl:text>_____________________________</xsl:text>
                    </fo:block>
                    <fo:block text-align="right">
                        <xsl:text>(потпис овлашћеног лица, односно руководиоца органа)</xsl:text>
                    </fo:block>




                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
