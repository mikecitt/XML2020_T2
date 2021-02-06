xquery version "3.1";
declare namespace odg = "http://localhost:8080/odgovori";
for $x in doc("odgovori.xml")//odg:odgovorSluzbenika
where $x/contains(@href,'%s')
return $x