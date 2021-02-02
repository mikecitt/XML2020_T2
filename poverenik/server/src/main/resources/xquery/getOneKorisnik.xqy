xquery version "3.1";
declare namespace kor = "http://localhost:8080/korisnici";
for $x in doc("korisnici.xml")//kor:korisnik
where $x//kor:email_adresa="%s"
return $x