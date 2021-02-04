xquery version "3.1";
declare namespace zlbod = "http://localhost:8080/zalbanaodluku";
for $x in doc("zalbanaodluku.xml")//zlbod:zalbenaodluku
return
    <zlbod:zalbenaodluku>
        {
            for $y in $x/zlbod:zalbanaodluku
            where $y//zlbod:informacije_o_podnosiocu_zalbe[@href='http://localhost:8080/korisnici/%s']
            return $y
        }
    </zlbod:zalbenaodluku>