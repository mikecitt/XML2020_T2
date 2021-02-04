xquery version "3.1";
declare namespace zah = "http://localhost:8080/zahtevcir";
for $x in doc("zahtev.xml")//zah:zahtev
where $x/@about = 'http://localhost:8080/zahtevcir/%s'
return $x