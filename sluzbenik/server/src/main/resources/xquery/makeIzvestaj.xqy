xquery version "3.1";
declare namespace zah = "http://localhost:8080/zahtevcir";
declare namespace izv = "http://localhost:8080/izvestaj";
declare namespace res = "http://localhost:8080/resenje";
declare namespace zal = "http://localhost:8080/zalba";

<izv:izvestaj>
    <izv:zalbe>
        <izv:na_odluku>
            <izv:broj_prihvacenih>
                {
                    count(doc("resenje.xml")//res:resenje[//res:rezultat_resenja[not(contains(text(), 'odbija'))]])
                }
            </izv:broj_prihvacenih>
            <izv:broj_odbijenih>
                {
                    count(doc("resenje.xml")//res:resenje//res:razlog_zalbe[contains(@href, 'zalbanaodluku')])
                }
            </izv:broj_odbijenih>
        </izv:na_odluku>
        <izv:cutanje>
            <izv:broj_prihvacenih>
                {
                    count(doc("resenje.xml")//res:resenje[//res:rezultat_resenja[not(contains(text(), 'odbija'))]])
                }
            </izv:broj_prihvacenih>
            <izv:broj_odbijenih>
                {
                    count(doc("db/resenje.xml")//res:resenje//res:razlog_zalbe[not(contains(@href, 'zalbanaodluku'))])
                }
            </izv:broj_odbijenih>
        </izv:cutanje>
        <izv:spisak>
            {
                for $r in doc("resenje.xml")//res:resenje
                return <izv:zalba>{$r//res:razlog/text()}</izv:zalba>
            }
        </izv:spisak>
    </izv:zalbe>
    <izv:zahtevi>
        <izv:broj_prihvacenih>
            {
                count(doc("zahtev.xml")//zah:status[text() = 'PRIHVACEN'])
            }
        </izv:broj_prihvacenih>
        <izv:broj_podnetih>
            {
                count(doc("zahtev.xml")//zah:status[text() = 'OBRADA'])
            }
        </izv:broj_podnetih>
        <izv:broj_odbijenih>
            {
                count(doc("zahtev.xml")//zah:status[text() = 'ODBIJEN'])
            }
        </izv:broj_odbijenih>
    </izv:zahtevi>
</izv:izvestaj>