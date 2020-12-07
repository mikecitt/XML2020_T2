package zahtevXMLobrada;

import zahtev.Zahtev;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import java.io.File;

public class ZahtevMarsahlling {
    public void test() throws Exception {
        try {
            System.out.println("[INFO] Zahtev: JAXB marshalling.\n");

            // DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("zahtev");

            // Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Unmarshalling generiÅ¡e objektni model na osnovu XML fajla
            Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("..\\docs\\instances\\zahtevcir.xml"));

            // Izmena nad objektnim modelom dodavanjem novog odseka
            zahtev.getOrgan().setNaziv("Novonastali organ");

            // Marshaller je objekat zaduÅ¾en za konverziju iz objektnog u XML model
            Marshaller marshaller = context.createMarshaller();

            // PodeÅ¡avanje marshaller-a
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
            marshaller.marshal(zahtev, System.out);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) throws Exception {
        ZahtevMarsahlling test = new ZahtevMarsahlling();
        test.test();
    }
}
