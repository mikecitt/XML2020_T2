<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:zal="http://localhost:8080/zalba" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <title>Zalba</title>
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
                <div style="font-family: Times New Roman; text-align: center; font-size: 11px" valign="top">
                    <div style="font-size: 10px; font-weight: bold">
        ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/
                        <span style="font-size: 11px">није поступио у целости</span>
        / ПО ЗАХТЕВУ
                    </div>
                    <div style="font-size: 10px; font-weight: bold">
        ТРАЖИОЦА У ЗАКОНСКОМ РОКУ (ЋУТАЊЕ УПРАВЕ)
                    </div>
                    <div>
                        <br />
                    </div>
                    <div style="font-size: 12px; text-align: left; font-weight: bold">
        Повереникy за информације од јавног значаја и заштиту података о
        личности
                    </div>
                    <div style="font-size: 11px; text-align: left">
        Адреса за пошту: Београд, Булевар краља Александрa бр. 15
                    </div>
                    <div>
                        <br />
                    </div>
                    <div>
                        <br />
                    </div>
                    <div style="text-align: left">
        У складу са чланом 22. Закона о слободном приступу информацијама од
        јавног значаја подносим:
                    </div>
                    <br/>
                    <div style="font-weight: bold">Ж А Л Б У</div>
                    <div>против</div>
                    <div style="border-bottom: 1px dotted; max-width: 500px; margin: 0 auto">
                        <xsl:value-of select="//zal:naziv_organa"/>
                    </div>
                    <div>( навести назив органа)</div>
                    <div>
                        <br />
                    </div>
                    <div>због тога што орган власти:</div>
                    <div style="font-weight: bold">
                        <xsl:choose>
                            <xsl:when test="//zal:razlog_zalbe = ''">
                                није поступио / није поступио у целости / у законском року
                            </xsl:when>
                            <xsl:when test="//zal:razlog_zalbe = 1">
                                <span style="text-decoration: underline">није поступио</span>
                                 / није поступио у целости /  у законском року 
                            </xsl:when>
                            <xsl:when test="//zal:razlog_zalbe = 2">
                                није поступио / 
                                <span style="text-decoration: underline">није поступио у целости</span>
                                 /  у законском року 
                            </xsl:when>
                            <xsl:when test="//zal:razlog_zalbe = 3">
                                није поступио / није поступио у целости /                     
                                <span style="text-decoration: underline">у законском року </span>
                            </xsl:when>
                            <xsl:otherwise>
                                није поступио / није поступио у целости / у законском року
                            </xsl:otherwise>
                        </xsl:choose>
                    </div>
                    <div>(подвући због чега се изјављује жалба)</div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: justify">
        по мом захтеву за слободан приступ информацијама од јавног значаја који
        сам поднео том органу дана

                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//zal:datum_zahteva, '[D01].[M01].[Y0001].')"/>
                        </span>

        године, а којим сам тражио/ла да ми се у складу са Законом о слободном
        приступу информацијама од јавног значаја омогући увид- копија документа
        који садржи информације о /у вези са :
                    </div>
                    <div style="text-align: justify">
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zal:podaci_zahteva"/>
                        </span>
                    </div>
                    <div>(навести податке о захтеву и информацији/ама)</div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: left; text-indent: 40px">
        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми
        приступ траженој/им информацији/ма.
                    </div>
                    <div style="text-align: left; text-indent: 40px">
        Као доказ , уз жалбу достављам копију захтева са доказом о предаји
        органу власти.
                    </div>
                    <div style="text-align: left; text-indent: 40px">
                        <span style="font-weight: bold">Напомена:</span> Код жалбе због
        непоступању по захтеву у целости, треба приложити и добијени одговор
        органа власти
                    </div>
                    <div style="text-align: right">
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zal:ime"/>
                        </span>
                    </div>
                    <div style="text-align: right">
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zal:prezime"/>
                        </span>
                    </div>
                    <div style="text-align: right">Подносилац жалбе / Име и презиме</div>
                    <br/>
                    <div style="text-align: right">
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zal:adresa"/>
                        </span>
                    </div>
                    <div style="text-align: right">адреса</div>
                    <br/>
                    <div style="text-align: right">
                        <span style="border-bottom: 1px dotted;">
                            <xsl:value-of select="//zal:drugi_kontakt"/>
                        </span>
                    </div>
                    <div style="text-align: right">други подаци за контакт</div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: left">
        У

                        <span style="text-align: center; border-bottom: 1px dotted">
                            <xsl:value-of select="//zal:mesto"/>
                        </span>

        , дана

                        <span style="text-align: center; border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//zal:datum, '[D01].[M01].')"/>
                        </span>

        20

                        <span style="text-align: center; border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//zal:datum, '[Y01].')"/>
                        </span>

        године
                    </div>
                </div>
                <br />
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>