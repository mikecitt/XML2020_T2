package zahtevXMLobrada;

import zahtev.Zahtev;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class ZahtevUnmarshalling {

    public void test() {
        try {

            System.out.println("[INFO] Zahtev: JAXB unmarshalling.\n");

            // DefiniÅ¡e se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("zahtev");

            // Unmarshaller je objekat zaduÅ¾en za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Unmarshalling generiÅ¡e objektni model na osnovu XML fajla
            Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("..\\docs\\instances\\zahtevcir.xml"));

            // Prikazuje unmarshallovan objekat
            printZahtev(zahtev);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void printZahtev(Zahtev zahtev) {

        // Prikaz naziva fakulteta (getter metoda)
        System.out.print("- Zahtev:  \n");
        System.out.print("\t- Organ: \n");
        System.out.print("\t\t- Naziv: " + zahtev.getOrgan().getNaziv() + "\n");
        System.out.print("\t\t- Sediste: " + zahtev.getOrgan().getSediste() + "\n");
        System.out.print("\t- Zahtevi: \n");
        System.out.print("\t\t- Svrha zahteva: " + zahtev.getZahtevi().getSvrhaZahteva() + "\n");
        System.out.print("\t\t- Nacin dostave: " + zahtev.getZahtevi().getNacinDostave() + "\n");
        System.out.print("\t\t- Opis informacije: " + zahtev.getZahtevi().getOpisInformacije() + "\n");
        System.out.print("\t- Detalji predaje: \n");
        System.out.print("\t\t- Mesto: " + zahtev.getDetaljiPredaje().getMesto() + "\n");
        System.out.print("\t\t- Datum: " + zahtev.getDetaljiPredaje().getDatum() + "\n");
        System.out.print("\t- Informacije o traziocu: \n");
        System.out.print("\t\t- Ime: " + zahtev.getInformacijeOTraziocu().getIme() + "\n");
        System.out.print("\t\t- Prezime: " + zahtev.getInformacijeOTraziocu().getPrezime() + "\n");
        System.out.print("\t\t- Adresa: " + zahtev.getInformacijeOTraziocu().getAdresa() + "\n");
        System.out.print("\t\t- Drugi kontakt: " + zahtev.getInformacijeOTraziocu().getDrugiKontakt() + "\n");

    }


    public static void main( String[] args ) {
        ZahtevUnmarshalling test = new ZahtevUnmarshalling();
        test.test();
    }
}