<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples"
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
                    <fo:block font-size="10px" font-weight="bold">
                         ЖАЛБА КАДА ОРГАН ВЛАСТИ НИЈЕ ПОСТУПИО/                        
                        <fo:inline font-size="11px">није поступио у целости</fo:inline>
                         / ПО ЗАХТЕВУ 
                    </fo:block>
                    <fo:block font-size="10px" font-weight="bold">
                        ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)
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
                    <fo:block text-align="left">
                        У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:
                    </fo:block>
                    <fo:block font-weight="bold">
                        Ж А Л Б У
                    </fo:block>
                    <fo:block>
                        против
                    </fo:block>
                    <fo:block>
                        ............................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        ............................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        ( навести назив органа)
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        због тога што орган власти: 
                    </fo:block>
                    <fo:block font-weight="bold">
                        није поступио / није поступио у целости /  у законском року 
                    </fo:block>
                    <fo:block>
                        (подвући  због чега се изјављује жалба)
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="justify">
                        по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана ….................... године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног значаја омогући увид- копија документа који садржи информације  о /у вези са :
                    </fo:block>
                    <fo:block>
                        ...................................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        ...................................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        ...................................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        ...................................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        (навести податке о захтеву и информацији/ама)
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="left" text-indent="40px">
                        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block text-align="left" text-indent="40px">
                        Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.
                    </fo:block>
                    <fo:block text-align="left" text-indent="40px">
                        <fo:inline font-weight="bold">Напомена:</fo:inline> Код жалбе  због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти
                    </fo:block>
                    <fo:block text-align="right">
                        .............................................................
                    </fo:block>
                    <fo:block text-align="right">
                        .............................................................
                    </fo:block>
                    <fo:block text-align="right">
                        Подносилац жалбе / Име и презиме
                    </fo:block>
                    <fo:block text-align="right">
                        .............................................................
                    </fo:block>
                    <fo:block text-align="right">
                        потпис
                    </fo:block>
                    <fo:block text-align="right">
                        .............................................................
                    </fo:block>
                    <fo:block text-align="right">
                        адреса
                    </fo:block>
                    <fo:block text-align="right">
                        .............................................................
                    </fo:block>
                    <fo:block text-align="right">
                        други подаци за контакт
                    </fo:block>
                    <fo:block text-align="right">
                        .............................................................
                    </fo:block>
                    <fo:block text-align="right">
                        адреса
                    </fo:block>
                    <fo:block text-align="left">
                        У................................., дана ............ 201....године
                    </fo:block>







                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
