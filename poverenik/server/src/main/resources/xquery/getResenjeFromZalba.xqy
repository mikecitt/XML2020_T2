xquery version "3.1";
declare namespace res = "http://localhost:8080/resenje";
for $x in doc("resenje.xml")//res:resenje
where $x//res:razlog_zalbe[contains(@href,'%s')]
return $x