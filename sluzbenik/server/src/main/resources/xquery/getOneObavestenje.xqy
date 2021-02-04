xquery version "3.1";
declare namespace obv = "http://localhost:8080/obavestenje";
for $x in doc("obavestenje.xml")//obv:obavestenje
where $x/@about = 'http://localhost:8080/obavestenje/%s'
return $x