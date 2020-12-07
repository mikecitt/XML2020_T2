package zalbanaodluku_obrada;

import zalbanaodluku.Zalbanaodluku;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ZalbaUnmarshalling {
    public void test() {
        try {
            JAXBContext context = JAXBContext.newInstance("zalbanaodluku");

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Zalbanaodluku zalbanaodluku = (Zalbanaodluku) unmarshaller.unmarshal(new File("..\\docs\\instances\\zalbanaodlukucir.xml"));

            ispisiZalbu(zalbanaodluku);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void ispisiZalbu(Zalbanaodluku zalbanaodluku) {
        System.out.print("- Zalba na odluku: \n");
        System.out.print("\t - Podnosilac zalbe: \n");
        System.out.print("\t\t - Ime: " + zalbanaodluku.getPodnosilacZalbe().getIme() + "\n");
        System.out.print("\t\t - Prezime: " + zalbanaodluku.getPodnosilacZalbe().getPrezime() + "\n");
        System.out.print("\t\t - Naziv: " + zalbanaodluku.getPodnosilacZalbe().getNaziv() + "\n");
        System.out.print("\t\t - Adresa: " + zalbanaodluku.getPodnosilacZalbe().getAdresa() + "\n");
        System.out.print("\t\t - Sediste: " + zalbanaodluku.getPodnosilacZalbe().getSediste() + "\n");
        System.out.print("\t - Naziv organa: " + zalbanaodluku.getNazivOrgana() + "\n");
        System.out.print("\t - Resenje: \n");
        System.out.print("\t\t - Broj resenja: " + zalbanaodluku.getResenje().getBrojResenja() + "\n");
        System.out.print("\t\t - Datum resenja: " + zalbanaodluku.getResenje().getDatumResenja() + "\n");
        System.out.print("\t - Datum podnosenja zahteva: " + zalbanaodluku.getDatumPodnosenjaZahteva() + "\n");
        System.out.print("\t - Opis zalbe: " + zalbanaodluku.getOpisZalbe() + "\n");
        System.out.print("\t - Detalji predaje: \n");
        System.out.print("\t\t - Mesto: " + zalbanaodluku.getDetaljiPredaje().getMesto() + "\n");
        System.out.print("\t\t - Datum: " + zalbanaodluku.getDetaljiPredaje().getDatum() + "\n");
        System.out.print("\t - Informacije o podnosioce zalbe: \n");
        System.out.print("\t\t - Ime: " + zalbanaodluku.getInformacijeOPodnosiocuZalbe().getIme() + "\n");
        System.out.print("\t\t - Prezime: " + zalbanaodluku.getInformacijeOPodnosiocuZalbe().getPrezime() + "\n");
        System.out.print("\t\t - Adresa: " + zalbanaodluku.getInformacijeOPodnosiocuZalbe().getAdresa() + "\n");
        System.out.print("\t\t - Drugi kontakt: " + zalbanaodluku.getInformacijeOPodnosiocuZalbe().getDrugiKontakt() + "\n");
    }

    public static void main(String[] args) {
        ZalbaUnmarshalling zalbaUnmarshalling = new ZalbaUnmarshalling();
        zalbaUnmarshalling.test();
    }
}
