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
                <fo:flow font-family="Times New Roman" text-align="center" font-size="12px" flow-name="xsl-region-body">
                    <fo:block>
                        ................................................................................................................................................
                    </fo:block>
                    <fo:block>
                        назив и седиште органа коме се захтев упућује
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
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        З А Х Т Е В
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold"> 
                        за приступ информацији од јавног значаја
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        <fo:leader />
                    </fo:block>
                    <fo:block font-size="14px" font-weight="bold">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align="justify" text-indent="40px"> 
                    	На основу члана 15. ст. 1. Закона о слободном приступу информацијама од јавног значаја („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и 36/10), од горе наведеног органа захтевам:*
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> обавештење да ли поседује тражену информацију;
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> увид у документ који садржи тражену информацију;
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> копију документа који садржи тражену информацију;
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> достављање копије документа који садржи тражену информацију:**
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> поштом
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> електронском поштом
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> факсом
                    </fo:block>
                    <fo:block text-indent="70px" text-align="left">
                        <fo:inline border-style="solid" font-size="6px" border-width="1pt">&#160;&#160;&#160;&#160;</fo:inline> на други начин:***_______________________________________
                    </fo:block>
                    <fo:block>
                        <fo:leader />
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        Овај захтев се односи на следеће информације:
                    </fo:block>
                    <fo:block text-indent="40px" text-align="left">
                        ______________________________________________________________
                    </fo:block>
                    <fo:block text-align="left">
                        _____________________________________________________________________
                    </fo:block>
                    <fo:block text-align="left">
                        _____________________________________________________________________
                    </fo:block>
                    <fo:block text-align="left" font-size="10px">
                        (навести што прецизнији опис информације која се тражи као и друге податке који олакшавају проналажење тражене информације)
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
                    <fo:block font-size="10px" text-align="right">
                        Тражилац информације/Име и презиме
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align-last="justify">
                        <fo:inline font-size="12px">У ________________,</fo:inline>
                        <fo:leader leader-pattern="space" />
                        <fo:inline font-size="9px">____________________________________</fo:inline>
                    </fo:block>
                    <fo:block text-align="right" font-size="10px">
                        адреса
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align-last="justify">
                        <fo:inline font-size="12px">дана______201__ године</fo:inline>
                        <fo:leader leader-pattern="space" />
                        <fo:inline font-size="9px">____________________________________</fo:inline>
                    </fo:block>
                    <fo:block text-align="right" font-size="10px">
                        други подаци за контакт
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align="right" font-size="9px">
                        ____________________________________
                    </fo:block>
                    <fo:block text-align="right" font-size="10px">
                        Потпис
                    </fo:block>
                    <fo:block font-size="10px">
                        <fo:leader />
                    </fo:block>
                    <fo:block text-align="left" font-size="10px">
                        __________________________________________
                    </fo:block>
                    <fo:block text-align="left" font-size="9px">
                        * У кућици означити која законска права на приступ информацијама желите да остварите.
                    </fo:block>
                    <fo:block text-align="left" font-size="9px">
                        ** У кућици означити начин достављања копије докумената.
                    </fo:block>
                    <fo:block text-align="left" font-size="9px">
                        *** Када захтевате други начин достављања обавезно уписати који начин достављања захтевате.
                    </fo:block>



                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
