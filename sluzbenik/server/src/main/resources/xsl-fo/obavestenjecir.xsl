<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples"
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
                        ______________________________
                    </fo:block>
                    <fo:block>
                        ______________________________
                    </fo:block>
                    <fo:block margin-left="2em">
                        (назив и седиште органа)
                    </fo:block>
                    <fo:block>
                        Број предмета: _________________
                    </fo:block>
                    <fo:block>
                        Датум:                        <fo:inline padding-left="3.5em">_________________</fo:inline>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        ______________________________________________
                    </fo:block>
                    <fo:block>
                        ______________________________________________
                    </fo:block>
                    <fo:block>
                        ______________________________________________
                    </fo:block>
                    <fo:block>
                        Име и презиме / назив / и адреса подносиоца захтева
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block font-size="12px" text-align="center" font-weight="bold">
                        О Б А В Е Ш Т Е Њ Е
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        о стављању на увид документа који садржи
                    </fo:block>
                    <fo:block text-align="center" font-weight="bold">
                        тражену информацију и о изради копије
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        На основу члана 16. ст. 1. Закона о слободном приступу информацијама од јавног значаја, поступајући по вашем захтеву за слободан приступ информацијама од _________год., којим сте тражили увид у документ/е са информацијама о / у вези са: 
                    </fo:block>
                    <fo:block>
                        ___________________________________________________________________________
                    </fo:block>
                    <fo:block>
                        ___________________________________________________________________________
                    </fo:block>
                    <fo:block>
                        ___________________________________________________________________________
                    </fo:block>
                    <fo:block text-align="center">
                        (опис тражене информације)
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-align="justify">
                        обавештавамо вас да дана _______________, у _____ часова, односно у времену од ____ до ___ часова, у просторијама органа у ___________________ ул. ____________________ бр. ______, канцеларија бр. ____ можете извршити увид у документ/е у коме је садржана тражена информација
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        Том приликом, на ваш захтев, може вам се издати и копија документа са траженом информацијом
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник РС“, бр. 8/06), и то: копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, дискете 20 динара, DVD 40 динара, аудио-касета – 150 динара, видео-касета 300 динара, претварање једне стране документа из физичког у електронски облик – 30 динара.
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block text-indent="40px">
                        Износ укупних трошкова израде копије документа по вашем захтеву износи ............ динара и уплаћује се на жиро-рачун Буџета Републике Србије бр. 840-742328-843-30, с позивом на број 97 – ознака шифре општине/града где се налази орган власти (из Правилника о условима и начину вођења рачуна – „Сл. гласник РС“, 20/07... 40/10). 
                    </fo:block>
                    <fo:block>
                        <fo:leader/>
                    </fo:block>
                    <fo:block>
                        Достављено:
                    </fo:block>
                    <fo:block>
                        1.	Именованом<fo:inline padding-left="5em">(М.П.)</fo:inline>
                    </fo:block>
                    <fo:block>
                        2.	Архиви
                    </fo:block>
                    <fo:block text-align="right">
                        _____________________________
                    </fo:block>
                    <fo:block text-align="right">
                        (потпис овлашћеног лица, односно руководиоца органа)
                    </fo:block>




                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
