xquery version "3.1";
declare namespace zah = "http://localhost:8080/zahtevcir";
for $x in doc("zahtev.xml")//zah:zahtevi
where $x//zah:informacije_o_traziocu[@href = 'http://localhost:8080/korisnici/%s']
return $x