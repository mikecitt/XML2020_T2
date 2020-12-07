package obrada_zalbacutanje;

import zalbacutanje.Zalbacutanje;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ZalbaCutanjeUnmarshalling {

    public void test() {
        try {
            JAXBContext context = JAXBContext.newInstance("zalbacutanje");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Zalbacutanje zalbaCutanje = (Zalbacutanje) unmarshaller.unmarshal(new File("..\\docs\\instances\\zalbacutanjecir.xml"));

            printZalba(zalbaCutanje);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void printZalba(Zalbacutanje zalbaCutanje) {

        System.out.println("Zalba");

        System.out.println("\tNaziv organa: " + zalbaCutanje.getNazivOrgana());
        System.out.println("\tRazlog zalbe: " + zalbaCutanje.getRazlogZalbe());
        System.out.println("\tDatum zahteva: " + zalbaCutanje.getDatumZahteva());
        System.out.println("\tPodaci zahteva: " + zalbaCutanje.getPodaciZahteva());

        printDetaljiPredaje(zalbaCutanje.getDetaljiPredaje());
        printInformacijeOPodnosiocuZalbe(zalbaCutanje.getInformacijeOPodnosiocuZalbe());
    }

    private void printDetaljiPredaje(Zalbacutanje.DetaljiPredaje detaljiPredaje) {

        System.out.println("\tDetalji predaje");

        System.out.println("\t\tMesto: " + detaljiPredaje.getMesto());
        System.out.println("\t\tDatum: " + detaljiPredaje.getDatum());
    }

    private void printInformacijeOPodnosiocuZalbe(Zalbacutanje.InformacijeOPodnosiocuZalbe informacijeOPodnosiocuZalbe) {

        System.out.println("\tInformacije o podnosiocu zalbe");

        System.out.println("\t\tIme: " + informacijeOPodnosiocuZalbe.getIme());
        System.out.println("\t\tPrezime: " + informacijeOPodnosiocuZalbe.getPrezime());
        System.out.println("\t\tAdresa: " + informacijeOPodnosiocuZalbe.getAdresa());
        System.out.println("\t\tDrugi kontakt: " + informacijeOPodnosiocuZalbe.getDrugiKontakt());
    }

    public static void main( String[] args ) {
        ZalbaCutanjeUnmarshalling test = new ZalbaCutanjeUnmarshalling();
        test.test();
    }
}