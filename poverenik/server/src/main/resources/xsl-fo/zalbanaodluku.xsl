<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:zlbod="http://localhost:8080/zalbanaodluku"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin-left="3.17cm" margin-top="2.54cm" margin-right="2.22cm" margin-bottom="2.54cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow font-family="Times New Roman" text-align="center" font-size="11px" flow-name="xsl-region-body">
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block font-size="10px" font-weight="bold">
                         ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ
                    </fo:block>
                    <fo:block font-size="10px" font-weight="bold">
                        <fo:inline text-decoration="underline">ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ</fo:inline> ЗА ПРИСТУП ИНФОРМАЦИЈИ
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block font-size="12px" text-align="left" font-weight="bold">
                        Повереникy за информације од јавног значаја и заштиту података о личности
                    </fo:block>
                    <fo:block font-size="11px" text-align="left">
                        Адреса за пошту:  Београд, Булевар краља Александрa бр. 15
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block font-weight="bold">
                        Ж А Л Б А
                    </fo:block>
                    <fo:block>
                        (
                        <fo:inline-container text-align="center" width="12cm">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:ime"/>
                                <fo:leader/>
                                <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:prezime"/>
                                <fo:leader/>
                                <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:naziv"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <fo:inline-container text-align="center" width="12cm">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:adresa"/>
                                <fo:leader/>
                                <xsl:value-of select="//zlbod:podnosilac_zalbe/zlbod:sediste"/>
                            </fo:block>
                        </fo:inline-container>   
                        )
                    </fo:block>
                    <fo:block>
                        Име, презиме, односно назив, адреса и седиште жалиоца)
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        против решења-закључка 
                    </fo:block>
                    <fo:block>
                        (
                        <fo:inline-container text-align="center" width="12cm">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:naziv_organa"/>
                            </fo:block>
                        </fo:inline-container>   
                        )
                    </fo:block>
                    <fo:block>
                        (назив органа који је донео одлуку)
                    </fo:block>
                    <fo:block text-align="left">
                        Број
                        <fo:inline-container text-align="center" width="4cm">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:broj_resenja"/>
                            </fo:block>
                        </fo:inline-container>   
                        од 
                        <fo:inline-container text-align="center" width="2cm">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="format-date(//zlbod:datum_resenja, '[D01].[M01].[Y0001].')"/>
                            </fo:block>
                        </fo:inline-container>  
                        године. 
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="justify">
                    Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-упутио/ла дана 
                        <fo:inline-container text-align="center" width="2cm">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="format-date(//zlbod:datum_podnosenja_zahteva, '[D01].[M01].[Y0001].')"/>
                            </fo:block>
                        </fo:inline-container> 
                    године и тако ми ускраћено-онемогућено остваривање уставног и законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у целости, односно у делу којим                     
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//zlbod:opis_zalbe"/>
                            <fo:leader/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-align="left">
                        јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                    </fo:block>
                    <fo:block text-align="left" text-indent="40px">
                        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block text-align="left" text-indent="40px">
                        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о слободном приступу информацијама од јавног значаја.
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="right">
                        <fo:inline-container width="170px">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:informacije_o_podnosiocu_zalbe/zlbod:ime"/>
&#160;
                                <xsl:value-of select="//zlbod:informacije_o_podnosiocu_zalbe/zlbod:prezime"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block text-align="right">
                        Подносилац жалбе / Име и презиме
                    </fo:block>
                    <fo:block text-align="left">
                        У 
                        <fo:inline-container width="120px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:mesto"/>
                            </fo:block>
                        </fo:inline-container>
                        ,
                    </fo:block>
                    <fo:block text-align="right">
                        <fo:inline-container width="170px">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:informacije_o_podnosiocu_zalbe/zlbod:adresa"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block text-align="right">
                        адреса
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        дана 
                        <fo:inline-container width="40px">
                            <fo:block border-bottom="1px dotted">
                                <xsl:value-of select="format-date(//zlbod:datum, '[D01].[M01].')"/>
                            </fo:block>
                        </fo:inline-container>
                        20
                        <fo:inline-container width="20px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="format-date(//zlbod:datum, '[Y01].')"/>
                            </fo:block>
                        </fo:inline-container>
                        године                        
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:leader/>
                        <fo:inline-container width="170px">
                            <fo:block text-align="right" border-bottom="1px dotted">
                                <xsl:value-of select="//zlbod:drugi_kontakt"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block text-align="right">
                        други подаци за контакт
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="left" font-weight="bold">
                        Напомена: 
                    </fo:block>
                    <fo:block text-align="left">
                        •	У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити. 
                    </fo:block>
                    <fo:block text-align="left" font-size="10px">
                        •	Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу као и копију одлуке органа која се оспорава жалбом                    
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
