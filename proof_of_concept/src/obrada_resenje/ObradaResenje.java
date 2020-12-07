package obrada_resenje;

import resenje.Resenje;
import resenje.Tosoba;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class ObradaResenje {
    private Resenje resenje;

    public void ucitajDokument() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("resenje");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        resenje = (Resenje) unmarshaller.unmarshal(new File("..\\docs\\instances\\resenje.xml"));
    }

    public void zapisiDokument() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("resenje");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(resenje, new File("..\\docs\\instances\\resenje.xml"));
    }

    public void promeniPodnositelja(String ime, String prezime) {
        Tosoba o = new Tosoba();
        o.setIme(ime);
        o.setPrezime(prezime);
        resenje.getRazlogZalbe().setPodnositeljZalbe(o);
    }

    public void ispisiOdgovor() {
        System.out.println("- Odgovor na žalbu:");
        System.out.println("\t" + resenje.getTeloResenja().getOdgovorNaZalbu());
    }

    public void ispisiResenje() {
        System.out.println("- Zaglavlje");

        System.out.println("\t- Rezultat rešenja: " + resenje.getZaglavlje().getRezultatResenja());
        System.out.println("\t- Informacije predmeta");
        System.out.println("\t\t- Broj predmeta: " + resenje.getZaglavlje().getInformacijePredmeta().getBrojPredmeta());
        System.out.println("\t\t- Datum predmeta: " + resenje.getZaglavlje().getInformacijePredmeta().getDatumPredmeta());
        System.out.println("\t- Datum rešenja: " + resenje.getZaglavlje().getDatumResenja());

        System.out.println("- Razlog žalbe");
        System.out.println("\t- Podnositelj žalbe");
        System.out.println("\t\t- Ime: " + resenje.getRazlogZalbe().getPodnositeljZalbe().getIme());
        System.out.println("\t\t- Prezime: " + resenje.getRazlogZalbe().getPodnositeljZalbe().getPrezime());
        System.out.println("\t- Razlog:");
        System.out.println("\t\t" + resenje.getRazlogZalbe().getRazlog());

        System.out.println("- Telo rešenja");
        System.out.println("\t- Odgovor na žalbu:");
        System.out.println("\t\t" + resenje.getTeloResenja().getOdgovorNaZalbu());
        System.out.println("\t- Obrazloženje");
        System.out.println("\t\t- Uvod:");
        System.out.println("\t\t\t" + resenje.getTeloResenja().getObrazlozenje().getUvodObrazlozenja());
        System.out.println("\t\t- Postupajući po:");
        System.out.println("\t\t\t" + resenje.getTeloResenja().getObrazlozenje().getPostupajuciPoZalbi());
        System.out.println("\t\t- Uvid:");
        System.out.println("\t\t\t" + resenje.getTeloResenja().getObrazlozenje().getUvid());
        System.out.println("\t\t- Dodatan uvid:");
        System.out.println("\t\t\t" + resenje.getTeloResenja().getObrazlozenje().getDodatanUvid());
        System.out.println("\t\t- Odgovor poverenika:");
        System.out.println("\t\t\t" + resenje.getTeloResenja().getObrazlozenje().getOdgovorPoverenika());

        System.out.println("\t- Upravni spor");
        System.out.println("\t\t- Mesto: " + resenje.getTeloResenja().getUpravniSpor().getMesto());
        System.out.println("\t- Upravni spor");
        System.out.println("\t\t- Rok prijema: " + resenje.getTeloResenja().getUpravniSpor().getRokPrijema());
        System.out.println("\t- Taksa");
        System.out.println("\t\t- Taksa: " + resenje.getTeloResenja().getUpravniSpor().getTaksa());

        System.out.println("- Poverenik");
        System.out.println("\t- Ime: " + resenje.getPoverenik().getIme());
        System.out.println("\t- Prezime: " + resenje.getPoverenik().getPrezime());
    }

    public void ispisiMeni() {
        System.out.println("********** RAD SA OBAVEŠTENJEM **********");
        System.out.println("1) Prikaži rešenje");
        System.out.println("2) Prikaži odgovor na žalbu");
        System.out.println("3) Promeni podnositelja žalbe");
        System.out.println("4) Sačuvaj dokument");
        System.out.println("q) Izađi");
        System.out.print("Vaš izbor: ");
    }

    public void pokreni() {
        String izbor = "";
        Scanner scanner = new Scanner(System.in);

        try {
            ucitajDokument();
        } catch (JAXBException ex) {
            System.out.println("Dokument nije uspešno učitan.\nAplikacija se gasi...");
            return;
        }

        do {
            ispisiMeni();
            izbor = scanner.next();

            switch (izbor) {
                case "1":
                    ispisiResenje();
                    break;
                case "2":
                    ispisiOdgovor();
                    break;
                case "3":
                    try {
                        String ime = null, prezime = null;
                        System.out.print("Unesite ime: ");
                        ime = scanner.next();
                        System.out.print("Unesite prezime: ");
                        prezime = scanner.next();
                        promeniPodnositelja(ime, prezime);
                    } catch (Exception ex) {
                        System.out.println("Desila se greška pri unosu...");
                    }
                    break;
                case "4":
                    try {
                        zapisiDokument();
                    } catch (Exception ex) {
                        System.out.println("Desila se greška pri čuvanju dokumenta...");
                    }
                    break;
                case "q":
                    System.out.println("Izlaz...");
                    break;
                default:
                    System.out.println("Pogrešna komanda...");
                    break;
            }
        } while(!izbor.equals("q"));
    }

    public static void main(String[] args) {
        ObradaResenje obradaResenje = new ObradaResenje();
        obradaResenje.pokreni();
    }
}
