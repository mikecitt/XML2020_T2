xquery version "3.1";
declare namespace zlbod = "http://localhost:8080/zalbanaodluku";
declare namespace zal = "http://localhost:8080/zalba";
let $zalbaodluku := "zalbanaodluku.xml"
let $zalbacutanje := "zalbacutanje.xml"
for $x in (doc($zalbaodluku)//zlbod:zalbanaodluku, doc($zalbacutanje)//zal:zalbacutanje)
where $x/contains(@about,'%s')
return $x