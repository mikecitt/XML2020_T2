xquery version "3.1";
declare namespace izv = "http://localhost:8080/izvestaj";
for $x in doc("izvestaj.xml")//izv:izvestaj
where $x/@about='http://localhost:8080/izvestaj/%s'
return $x