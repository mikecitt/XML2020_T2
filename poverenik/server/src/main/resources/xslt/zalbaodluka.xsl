<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:zlbod="http://localhost:8080/zalbanaodluku" version="2.0">

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
                    <div>
                        <br/>
                    </div>
                    <div style="font-size: 10px; font-weight: bold">
        ЖАЛБА ПРОТИВ ОДЛУКЕ ОРГАНА ВЛАСТИ КОЈОМ ЈЕ
                    </div>
                    <div style="font-size: 10px; font-weight: bold">
                        <span style="text-decoration: underline">ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ</span
        >
        ЗА ПРИСТУП ИНФОРМАЦИЈИ
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="font-size: 12px; text-align: left; font-weight: bold">
        Повереникy за информације од јавног значаја и заштиту података о
        личности
                    </div>
                    <div style="font-size: 11px; text-align: left">
        Адреса за пошту: Београд, Булевар краља Александрa бр. 15
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="font-weight: bold">Ж А Л Б А</div>
                    <div>
                        <div style="border-bottom: 1px dotted; margin: 0 auto; max-width: 400px">
                            <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:ime"/>
&#160;

                            <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:prezime"/>
&#160;

                            <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:naziv"/>
                        </div>
                    </div>
                    <div>
                        <div style="border-bottom: 1px dotted; margin: 0 auto; max-width: 400px">
                            <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:adresa"/>
&#160;

                            <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:sediste"/>
                        </div>
                    </div>
                    <div>Име, презиме, односно назив, адреса и седиште жалиоца)</div>
                    <div>
                        <br/>
                    </div>
                    <div>против решења-закључка</div>
                    <div>
                        <div style="border-bottom: 1px dotted; max-width: 400px; margin: 0 auto">
                            <xsl:value-of select="//zlbod:naziv_organa"/>
                        </div>
                    </div>
                    <div>(назив органа који је донео одлуку)</div>
                    <div style="text-align: left">
        Број

                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zlbod:broj_resenja"/>
                        </span>

        од

                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//zlbod:datum_resenja, '[D01].[M01].[Y0001].')"/>
                        </span>

        године.
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: justify">
        Наведеном одлуком органа власти (решењем, закључком, обавештењем у
        писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен
        је мој захтев који сам поднео/ла-упутио/ла дана

                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="format-date(//zlbod:datum_podnosenja_zahteva, '[D01].[M01].[Y0001].')"/>
                        </span>

        године и тако ми ускраћено-онемогућено остваривање уставног и законског
        права на слободан приступ информацијама од јавног значаја. Oдлуку
        побијам у целости, односно у делу којим
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zlbod:opis_zalbe"/>
                        </span>
                    </div>
                    <div style="text-align: left">
        јер није заснована на Закону о слободном приступу информацијама од
        јавног значаја.
                    </div>
                    <div style="text-align: left; text-indent: 40px">
        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,
        поништи одлука првостепеног органа и омогући ми приступ траженој/им
        информацији/ма.
                    </div>
                    <div style="text-align: left; text-indent: 40px">
        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст.
        1. Закона о слободном приступу информацијама од јавног значаја.
                    </div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: right">
                        <span style="border-bottom: 1px dotted">
                            <xsl:value-of select="//zlbod:informacije_o_podnosiocu_zalbe/zlbod:ime"/>
&#160;
                            <xsl:value-of select="//zlbod:informacije_o_podnosiocu_zalbe/zlbod:prezime"/>
                        </span>
                    </div>
                    <div style="text-align: right">Подносилац жалбе / Име и презиме</div>
                    <br/>
                    <div style="text-align: left">
                        <span style="display:block; float:left;">У

                            <span style="text-align: center; border-bottom: 1px dotted">
                                <xsl:value-of select="//zlbod:mesto"/>
                            </span>
                            ,
                        </span>
                    </div>
                    <div style="text-align: right">
                        <span style="display:block; float:right;">
                            <span style="border-bottom: 1px dotted">
                                <xsl:value-of select="//zlbod:informacije_o_podnosiocu_zalbe/zlbod:adresa"/>
                            </span>

                        </span>
                    </div>
                    <br/>
                    <div style="text-align: right">адреса</div>
                    <div>
                        <br/>
                    </div>
                    <div>
                        <span style="display:block; float:left;">
        дана

                            <span style="border-bottom: 1px dotted">
                                <xsl:value-of select="format-date(//zlbod:datum, '[D01].[M01].')"/>
                            </span>

        20

                            <span style="text-align: center; border-bottom: 1px dotted">
                                <xsl:value-of select="format-date(//zlbod:datum, '[Y01].')"/>
                            </span>

        године                   
                        </span>
                        <span style="display:block; float:right;">

                            <span style="text-align: right; border-bottom: 1px dotted">
                                <xsl:value-of select="//zlbod:drugi_kontakt"/>
                            </span>
                        </span>
                    </div>
                    <br/>
                    <div style="text-align: right">други подаци за контакт</div>
                    <div>
                        <br/>
                    </div>
                    <div style="text-align: left; font-weight: bold">Напомена:</div>
                    <div style="text-align: left">
        • У жалби се мора навести одлука која се побија (решење, закључак,
        обавештење), назив органа који је одлуку донео, као и број и датум
        одлуке. Довољно је да жалилац наведе у жалби у ком погледу је
        незадовољан одлуком, с тим да жалбу не мора посебно образложити. Ако
        жалбу изјављује на овом обрасцу, додатно образложење може посебно
        приложити.
                    </div>
                    <div style="text-align: left; font-size: 10px">
        • Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој
        предаји-упућивању органу као и копију одлуке органа која се оспорава
        жалбом
                    </div>
                </div>
                <br />
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>