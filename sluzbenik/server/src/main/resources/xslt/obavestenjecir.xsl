<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:obv="http://localhost:8080/obavestenje" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <title>XSL FO Document</title>
                <meta http-equiv="Content-Style-Type" content="text/css" />
                <style type="text/css">
      a {
        color: black;
        border: none;
        text-decoration: none;
      }
      img {
        border: none;
      }
                </style>
            </head>
            <body bgcolor="white" marginwidth="6" marginheight="6" leftmargin="6" topmargin="6">
                <br />
                <div style="font-family: Times New Roman; text-align: left; font-size: 11px" valign="top">
                    <div>
                        <div style="text-align: center; border-bottom: 1px dotted; max-width: 150px">
                            <xsl:value-of select="//obv:naziv_organa"/>
                        </div>
                    </div>
                    <div>
                        <div style="text-align: center; border-bottom: 1px dotted; max-width: 150px">
                            <xsl:value-of select="//obv:sediste_organa"/>
                        </div>
                    </div>
                    <div style="margin-left: 2em">(назив и седиште органа)</div>
                    <div>
        Број предмета:

                        <span style="text-align: center; border-bottom: 1px dotted;">
                            <xsl:value-of select="//obv:broj_predmeta"/>
                        </span>
                    </div>
                    <div>
        Датум:
                        <span style="padding-left: 3.5em">
                            <span style="text-align: center; border-bottom: 1px dotted">
                                <xsl:value-of select="format-date(//obv:zaglavlje/obv:datum, '[D01].[M01].[Y0001].')"/>
                            </span>
                        </span>
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div>
                        <div style="text-align: center; border-bottom: 1px dotted; max-width: 250px">
                            <xsl:value-of select="//obv:podnosioc_zahteva/obv:ime"/>
                            <xsl:text>&#160;</xsl:text>
                            <xsl:value-of select="//obv:podnosioc_zahteva/obv:prezime"/>
                        </div>
                    </div>
                    <div>
                        <div style="text-align: center; border-bottom: 1px dotted; max-width: 250px">
                            <xsl:value-of select="//obv:podnosioc_zahteva/obv:naziv"/>
&#160;
                        </div>
                    </div>
                    <div>
                        <div style="text-align: center; border-bottom: 1px dotted; max-width: 250px">
                            <xsl:value-of select="//obv:podnosioc_zahteva/obv:adresa"/>
&#160;
                        </div>
                    </div>
                    <div>Име и презиме / назив / и адреса подносиоца захтева</div>
                    <div>
                        <br/>
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="font-size: 12px; text-align: center; font-weight: bold">
        О Б А В Е Ш Т Е Њ Е
                    </div>
                    <div style="text-align: center; font-weight: bold">
        о стављању на увид документа који садржи
                    </div>
                    <div style="text-align: center; font-weight: bold">
        тражену информацију и о изради копије
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-indent: 40px">
        На основу члана 16. ст. 1. Закона о слободном приступу информацијама од
        јавног значаја, поступајући по вашем захтеву за слободан приступ
        информацијама од
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//obv:datum_informacija, '[D01].[M01].[Y0001].')"/>
                        </span>
        год., којим сте тражили увид у документ/е са информацијама о / у вези
        са:
                    </div>
                    <div style="text-align: justify">
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:opis_informacije"/>
                        </span>
                    </div>
                    <div style="text-align: center">(опис тражене информације)</div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: justify">
        обавештавамо вас да дана
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//obv:vreme/obv:datum, '[D01].[M01].[Y0001].')"/>
                        </span>
        , у
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:vreme/obv:cas"/>
                        </span>
        часова, односно у времену од
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:trajanje/obv:pocetak"/>
                        </span>
        до
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:trajanje/obv:kraj"/>
                        </span>
        часова, у просторијама органа у
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:mesto"/>
                        </span>
        ул.
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:ulica"/>
                        </span>
        бр.
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:broj"/>
                        </span>
        , канцеларија бр.
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:adresa/obv:br_kancelarije"/>
                        </span>
        можете                        <b>извршити увид</b> у документ/е у коме је садржана тражена информација
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-indent: 40px">
        Том приликом, на ваш захтев, може вам се издати и копија документа са
        траженом информацијом
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div>
        Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“,
        бр. 8/06), и то: копија стране А4 формата износи 3 динара, А3 формата 6
        динара, CD 35 динара, дискете 20 динара, DVD 40 динара, аудио-касета –
        150 динара, видео-касета 300 динара, претварање једне стране документа
        из физичког у електронски облик – 30 динара.
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-indent: 40px">
        Износ укупних трошкова израде копије документа по вашем захтеву износи
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//obv:trosak"/>
                        </span>
        динара и уплаћује се на жиро-рачун Буџета Републике Србије бр.
        840-742328-843-30, с позивом на број 97 – ознака шифре општине/града где
        се налази орган власти (из Правилника о условима и начину вођења рачуна
        – „Сл. гласник РС“, 20/07... 40/10).
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div>Достављено:</div>
                    <div>
        1.     Именованом
                        <span style="padding-left: 5em">(М.П.)</span>
                    </div>
                    <div>2.     Архиви</div>
                    <div style="text-align: right">_____________________________</div>
                    <div style="text-align: right">
        (потпис овлашћеног лица, односно руководиоца органа)
                    </div>
                </div>
                <br />
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>