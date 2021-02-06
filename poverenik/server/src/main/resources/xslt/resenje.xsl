<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:res="http://localhost:8080/resenje" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <title>Resenje</title>
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
                <div style="font-family: Times New Roman; text-align: justify; font-size: 12px" valign="top">
                    <div>
                        <br/>
                    </div>
                    <div>Решење -                        <xsl:value-of select="//res:rezultat_resenja"/>
                    </div>
                    <div>Бр.                        <xsl:value-of select="//res:broj_predmeta"/>
/                        <xsl:value-of select="//res:datum_predmeta"/>
     Датум:                        <xsl:value-of select="format-date(//res:datum_resenja, '[D01].[M01].[Y0001].')"/>
 године.</div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-indent: 40px">
        Повереник за информације од јавног значаја и заштиту података о
        личности, у поступку по жалби коју је изјавио                        
                        <xsl:value-of select="substring(//res:podnositelj_zalbe/res:ime, 1, 1)"/>
                        <xsl:value-of select="substring(//res:podnositelj_zalbe/res:prezime, 1, 1)"/>
,                        <xsl:value-of select="//res:razlog"/>
, доноси 
                    </div>
                    <br/>
                    <div style="text-align: center">Р Е Ш Е Њ Е</div>
                    <div style="text-indent: 40px">
                        <xsl:value-of select="//res:odgovor_na_zalbu"/>
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: center">О б р а з л о ж е њ е</div>
                    <div style="text-indent: 40px">
                        <xsl:value-of select="//res:uvod_obrazlozenja"/>
                    </div>
                    <div style="text-indent: 40px">Поступајући по жалби,                        <xsl:value-of select="//res:postupajuci_po_zalbi"/>
                    </div>
                    <div style="text-indent: 40px">
        По разматрању жалбе и осталих списа овог предмета, донета је одлука као
        у диспозитиву решења из следећих разлога:
                    </div>
                    <div style="text-indent: 40px">
        Увидом у списе предмета утврђено је да је                        <xsl:value-of select="//res:uvid"/>
                    </div>
                    <div style="text-indent: 40px">Имајући у виду                        <xsl:value-of select="//res:dodatan_uvid"/>
                    </div>
                    <div style="text-indent: 40px">
                        <xsl:value-of select="//res:odgovor_poverenika"/>
                    </div>
                    <div style="text-indent: 40px">Упутство о правном средству:</div>
                    <div style="text-indent: 40px">
        Против овог решења није допуштена жалба већ се, у складу са Законом о
        управним споровима, може покренути управни спор тужбом Управном суду у                        <xsl:value-of select="//res:upravni_spor/res:mesto"/>
,
        у року од                        <xsl:value-of select="//res:upravni_spor/res:rok_prijema"/>
 дана од дана пријема решења. Такса на тужбу износи                        <xsl:value-of select="//res:upravni_spor/res:taksa"/>
 динара.
                    </div>
                    <div style="text-align: right">ПОВЕРЕНИК</div>
                    <div style="text-align: right">
                        <xsl:value-of select="//res:poverenik/res:ime"/>
                        &#160;
                        <xsl:value-of select="//res:poverenik/res:prezime"/>
                    </div>
                    <div></div>
                </div>
                <br />
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>