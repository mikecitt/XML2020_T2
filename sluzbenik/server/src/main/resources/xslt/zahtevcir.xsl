<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:zah="http://localhost:8080/zahtevcir" version="2.0">

  <xsl:template match="/">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Zahtev</title>
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
        <div style="font-family: Times New Roman; text-align: center; font-size: 12px" valign="top">
          <div>
            <div style="text-align: center; border-bottom: 1px dotted">
              <xsl:value-of select="//zah:naziv"/>
&#160;
              <xsl:value-of select="//zah:sediste"/>
            </div>
          </div>
          <div>назив и седиште органа коме се захтев упућује</div>
          <div>
            <br/>
          </div>
          <div>
            <br/>
          </div>
          <div>
            <br/>
          </div>
          <div style="font-size: 14px; font-weight: bold">З А Х Т Е В</div>
          <div style="font-size: 14px; font-weight: bold">
        за приступ информацији од јавног значаја
          </div>
          <div style="font-size: 14px; font-weight: bold">
            <br/>
          </div>
          <div style="font-size: 14px; font-weight: bold">
            <br/>
          </div>
          <div style="text-align: justify; text-indent: 40px">
        На основу члана 15. ст. 1. Закона о слободном приступу информацијама од
        јавног значаја („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и
        36/10), од горе наведеног органа захтевам:*
          </div>
          <div>
            <br/>
          </div>
          <div style="text-indent: 40px; text-align: left">
            <xsl:choose>
              <xsl:when test="//zah:svrha_zahteva = 1">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            font-size: 6px;
            border-width: 1pt;
          ">        &#160;&#160;        </span
        >
              </xsl:otherwise>
            </xsl:choose>

        обавештење да ли поседује тражену информацију;
          </div>
          <div style="text-indent: 40px; text-align: left">
            <xsl:choose>
              <xsl:when test="//zah:svrha_zahteva = 2">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>


        увид у документ који садржи тражену информацију;
          </div>
          <div style="text-indent: 40px; text-align: left">
            <xsl:choose>
              <xsl:when test="//zah:svrha_zahteva = 3">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>

        копију документа који садржи тражену информацију;
          </div>
          <div style="text-indent: 40px; text-align: left">
            <xsl:choose>
              <xsl:when test="//zah:svrha_zahteva = 4">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>

        достављање копије документа који садржи тражену информацију:**
          </div>
          <div style="text-indent: 70px; text-align: left">
            <xsl:choose>
              <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave = '1')">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>

        поштом
          </div>
          <div style="text-indent: 70px; text-align: left">
            <xsl:choose>
              <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave = '2')">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>

        електронском поштом
          </div>
          <div style="text-indent: 70px; text-align: left">
            <xsl:choose>
              <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave = '3')">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>

        факсом
          </div>
          <div style="text-indent: 70px; text-align: left">
            <xsl:choose>
              <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave != '1') and (//zah:nacin_dostave != '2') and (//zah:nacin_dostave != '3')">
                <span style="
            border-style: solid;
            background-color: black;
            font-size: 6px;
            border-width: 1pt;
          ">            &#160;&#160;    </span
        >
              </xsl:when>
              <xsl:otherwise>
                <span style="
            border-style: solid;
            background-color: white;
            font-size: 6px;
            border-width: 1pt;
          ">       &#160;&#160;         </span
        >
              </xsl:otherwise>
            </xsl:choose>

        на други начин:***

            <xsl:choose>
              <xsl:when test="(//zah:nacin_dostave != '') and (//zah:nacin_dostave != '1') and (//zah:nacin_dostave != '2') and (//zah:nacin_dostave != '3')">
                <span style="border-bottom: 1px dotted">
                  <xsl:value-of select="//zah:nacin_dostave"/>
                </span>
              </xsl:when>
              <xsl:otherwise>
                <span style="border-bottom: 1px dotted"></span>
              </xsl:otherwise>
            </xsl:choose>



            <span style="border-bottom: 1px dotted"></span>
          </div>
          <div>
            <br/>
          </div>
          <div style="text-indent: 40px; text-align: left">
        Овај захтев се односи на следеће информације:
          </div>
          <div style="text-indent: 40px; font-size: 11; text-align: left">
            <span style="border-bottom: 1px dotted">
              <xsl:value-of select="//zah:opis_informacije"/>
            </span>
          </div>
          <div style="text-align: left; font-size: 10px">
        (навести што прецизнији опис информације која се тражи као и друге
        податке који олакшавају проналажење тражене информације)
          </div>
          <div style="font-size: 9px">
            <br/>
          </div>
          <div style="font-size: 9px">
            <br/>
          </div>
          <div style="font-size: 10px">
            <br/>
          </div>
          <div style="font-size: 9px">
            <br/>
          </div>
          <div style="font-size: 12px; text-align: right">
            <span style="border-bottom: 1px dotted">
              <xsl:value-of select="//zah:ime"/>
              <xsl:text>&#160;</xsl:text>
              <xsl:value-of select="//zah:prezime"/>
            </span>
          </div>
          <div style="font-size: 10px; text-align: right">
        Тражилац информације/Име и презиме
          </div>
          <div style="font-size: 10px">
            <br/>
          </div>
          <div>
            <span style="font-size: 12px">
              <span style="display:block; float:left;">У
                <span style="border-bottom: 1px dotted">
                  <xsl:value-of select="//zah:detalji_predaje/zah:mesto"/>
                </span>
              </span>
          ,
            </span>

            <span style="display:block; float:right;  font-size: 12px">
              <span style="border-bottom: 1px dotted">
                <xsl:value-of select="//zah:adresa"/>
              </span>
            </span>
          </div>
          <div style="text-align: right; font-size: 10px">адреса</div>
          <div style="font-size: 10px">
            <br/>
          </div>
          <div>
            <span style="font-size: 12px">
              <span style="display:block; float:left;">
          дана
                <span style="border-bottom: 1px dotted">
                  <xsl:value-of select="format-date(//zah:datum, '[D01].[M01].')"/>
                </span>
          20
                <span style="border-bottom: 1px dotted">
                  <xsl:value-of select="format-date(//zah:datum, '[Y01].')"/>
                </span>
          године
              </span>
            </span>
            <span style="display:block; float:right;">
              <span style="border-bottom: 1px dotted">
                <xsl:value-of select="//zah:drugi_kontakt"/>
              </span>
            </span>
          </div>
          <br/>
          <div style="text-align: right; font-size: 10px">
        други подаци за контакт
          </div>
          <div style="font-size: 10px">
            <br/>
          </div>
          <div style="font-size: 10px">
            <br/>
          </div>
          <div style="text-align: left; font-size: 10px">
        __________________________________________
          </div>
          <div style="text-align: left; font-size: 9px">
        * У кућици означити која законска права на приступ информацијама желите
        да остварите.
          </div>
          <div style="text-align: left; font-size: 9px">
        ** У кућици означити начин достављања копије докумената.
          </div>
          <div style="text-align: left; font-size: 9px">
        *** Када захтевате други начин достављања обавезно уписати који начин
        достављања захтевате.
          </div>
        </div>
        <br />
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>