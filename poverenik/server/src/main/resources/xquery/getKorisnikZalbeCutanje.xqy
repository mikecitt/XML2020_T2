xquery version "3.1";
declare namespace zal = "http://localhost:8080/zalba";
for $x in doc("zalbacutanje.xml")//zal:zalbecutanje
return
    <zal:zalbecutanje>
        {
            for $y in $x/zal:zalbacutanje
            where $y//zal:informacije_o_podnosiocu_zalbe[@href='http://localhost:8080/korisnici/%s']
            return $y
        }
    </zal:zalbecutanje>