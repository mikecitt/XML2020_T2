xquery version "3.1";
declare namespace zlbod = "http://localhost:8080/zalbanaodluku";
declare namespace zal = "http://localhost:8080/zalba";
let $zalbaodluku := "zalbanaodluku.xml"
let $zalbacutanje := "zalbacutanje.xml"
let $korisnikId := "http://localhost:8080/korisnici/%s"
for $x in (doc($zalbaodluku)//zlbod:zalbanaodluku, doc($zalbacutanje)//zal:zalbacutanje)
where $x/contains(@about,'%s') and
($x//zlbod:informacije_o_podnosiocu_zalbe[@href=$korisnikId] or
 $x//zal:informacije_o_podnosiocu_zalbe[@href=$korisnikId])
return $x