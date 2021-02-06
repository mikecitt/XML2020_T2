xquery version "3.1";
declare namespace res = "http://localhost:8080/resenje";
for $x in doc("resenje.xml")//res:resenje
where $x/@about='http://localhost:8080/resenje/%s'
return $x