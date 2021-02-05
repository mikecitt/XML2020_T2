<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:zah="http://localhost:8080/zahtevcir"
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bookstore-page">
                    <fo:region-body margin-left="3.17cm" margin-top="2.54cm" margin-right="3.17cm" margin-bottom="2.54cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bookstore-page">
                <fo:flow font-family="Times New Roman" text-align="center" font-size="12px" flow-name="xsl-region-body">
                    <fo:block>
                        <fo:inline-container width="450px">
                            <fo:block text-align="center" border-bottom="1px dotted">
                                <xsl:value-of select="//zah:naziv"/>
                                <fo:leader/>
                                <xsl:value-of select="//zah:sediste"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>
                    <fo:block>
                        <xsl:text>назив и седиште органа коме се захтев упућује</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        <xsl:text>З А Х Т Е В</xsl:text>
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        <xsl:text>за приступ информацији од јавног значаја</xsl:text>
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align="justify" text-indent="40px">
                        <xsl:text>На основу члана 15. ст. 1. Закона о слободном приступу информацијама од јавног 
                        значаја („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), од горе наведеног органа захтевам:*</xsl:text>
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="//zah:svrha_zahteva = 1">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> обавештење да ли поседује тражену информацију;</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="//zah:svrha_zahteva = 2">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> увид у документ који садржи тражену информацију;</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="//zah:svrha_zahteva = 3">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> копију документа који садржи тражену информацију;</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="//zah:svrha_zahteva = 4">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> достављање копије документа који садржи тражену информацију:**</xsl:text>
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave = '1')">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> поштом</xsl:text>
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave = '2')">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> електронском поштом</xsl:text>
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave = '3')">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> факсом</xsl:text>
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <xsl:choose>
                            <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave != '1') and (//zah:nacin_dostave != '2') and (//zah:nacin_dostave != '3')">
                                <fo:inline border-style="solid" background-color="black" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:text> на други начин:***</xsl:text>
                        <xsl:choose>
                            <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave != '1') and (//zah:nacin_dostave != '2') and (//zah:nacin_dostave != '3')">
                                <fo:inline border-bottom="1px dotted">
                                    <xsl:value-of select="//zah:nacin_dostave"/>
                                </fo:inline>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:inline border-bottom="1px dotted">
                                    <fo:leader/>
                                </fo:inline>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <xsl:text>Овај захтев се односи на следеће информације:</xsl:text>
                    </fo:block>
                    <fo:block text-indent="40px" font-size="11" text-align="left">
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//zah:opis_informacije"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-align="left" font-size="10px">
                        <xsl:text>(навести што прецизнији опис информације која се тражи као и друге податке који олакшавају проналажење тражене информације)</xsl:text>
                    </fo:block>
                    <fo:block font-size="9px">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="9px">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="9px">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="12px" text-align="right">
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//zah:ime"/>
                            <xsl:text>&#160;</xsl:text>
                            <xsl:value-of select="//zah:prezime"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-size="10px" text-align="right">
                        <xsl:text>Тражилац информације/Име и презиме</xsl:text>
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align-last="justify">
                        <fo:inline font-size="12px">
                            <xsl:text>У </xsl:text>
                            <fo:inline border-bottom="1px dotted">
                                <xsl:value-of select="//zah:detalji_predaje/zah:mesto"/>
                            </fo:inline>
                            <xsl:text>,</xsl:text>
                        </fo:inline>
                        <fo:leader leader-pattern="space" />
                        <fo:inline font-size="12px">
                            <fo:inline border-bottom="1px dotted">
                                <xsl:value-of select="//zah:adresa"/>
                            </fo:inline>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-align="right" font-size="10px">
                        <xsl:text>адреса</xsl:text>
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align-last="justify">
                        <fo:inline font-size="12px">
                            <xsl:text>дана</xsl:text>
                            <fo:inline border-bottom="1px dotted">
                                <xsl:value-of select="format-date(//zah:datum, '[D01].[M01].')"/>
                            </fo:inline>
                            <xsl:text>20</xsl:text>
                            <fo:inline border-bottom="1px dotted">
                                <xsl:value-of select="format-date(//zah:datum, '[Y01].')"/>
                            </fo:inline>
                            <xsl:text> године</xsl:text>
                        </fo:inline>
                        <fo:leader leader-pattern="space" />
                        <fo:inline border-bottom="1px dotted">
                            <xsl:value-of select="//zah:drugi_kontakt"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block text-align="right" font-size="10px">
                        <xsl:text>други подаци за контакт</xsl:text>
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align="left" font-size="10px">
                        __________________________________________
                    </fo:block>
                    <fo:block text-align="left" font-size="9px">
                        <xsl:text>* У кућици означити која законска права на приступ информацијама желите да остварите.</xsl:text>
                    </fo:block>
                    <fo:block text-align="left" font-size="9px">
                        <xsl:text>** У кућици означити начин достављања копије докумената.</xsl:text>
                    </fo:block>
                    <fo:block text-align="left" font-size="9px">
                        <xsl:text>*** Када захтевате други начин достављања обавезно уписати који начин достављања захтевате.</xsl:text>
                    </fo:block>



                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
