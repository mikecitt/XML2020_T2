package obrada_obavestenje;

import obavestenje.Obavestenje;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class ObradaObavestenje {
    private Obavestenje obavestenje;

    public void ucitajDokument() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("obavestenje");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        obavestenje = (Obavestenje) unmarshaller.unmarshal(new File("..\\docs\\instances\\obavestenjecir.xml"));
    }

    public void zapisiDokument() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("obavestenje");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obavestenje, new File("..\\docs\\instances\\obavestenjecir.xml"));
    }

    public void promeniUkupanTrosak(String trosak) {
        obavestenje.getTelo().setTrosak(trosak);
    }

    public void ispisiInformacije() {
        System.out.println("- Tražena informacija");
        System.out.println("\t- Datum informacije: " + obavestenje.getTelo().getTrazenaInformacija().getDatumInformacija());
        System.out.println("\t- Opis informacije: \n\t\t\t" + obavestenje.getTelo().getTrazenaInformacija().getOpisInformacije());
    }

    public void ispisiObavestenje() {
        System.out.println("- Zaglavlje");

        System.out.println("\t- Organ");
        System.out.println("\t\t- Naziv: " + obavestenje.getZaglavlje().getOrgan().getNazivOrgana());
        System.out.println("\t\t- Sedište: " + obavestenje.getZaglavlje().getOrgan().getSedisteOrgana());
        System.out.println("\t- Broj predmeta: " + obavestenje.getZaglavlje().getBrojPredmeta());
        System.out.println("\t- Datum predmeta: " + obavestenje.getZaglavlje().getDatum());
        System.out.println("\t- Podnosioc zahteva");
        System.out.println("\t\t- Ime: " + obavestenje.getZaglavlje().getPodnosiocZahteva().getIme());
        System.out.println("\t\t- Prezime: " + obavestenje.getZaglavlje().getPodnosiocZahteva().getPrezime());
        System.out.println("\t\t- Naziv: " + obavestenje.getZaglavlje().getPodnosiocZahteva().getNaziv());
        System.out.println("\t\t- Adresa: " + obavestenje.getZaglavlje().getPodnosiocZahteva().getAdresa());

        System.out.println("- Telo");

        System.out.println("\t- Tražena informacija");
        System.out.println("\t\t- Datum informacije: " + obavestenje.getTelo().getTrazenaInformacija().getDatumInformacija());
        System.out.println("\t\t- Opis informacije: \n\t\t\t" + obavestenje.getTelo().getTrazenaInformacija().getOpisInformacije());

        System.out.println("\t- Uvid");
        System.out.println("\t\t- Datum i vreme za uvid");
        System.out.println("\t\t\t- Datum za uvid: " + obavestenje.getTelo().getUvid().getVreme().getDatum());
        System.out.println("\t\t\t- Čas: " + obavestenje.getTelo().getUvid().getVreme().getCas());
        System.out.println("\t\t\t- Trajanje od " + obavestenje.getTelo().getUvid().getVreme().getTrajanje().getPocetak() +
                " do " + obavestenje.getTelo().getUvid().getVreme().getTrajanje().getKraj());

        System.out.println("\t\t- Adresa za uvid");
        System.out.println("\t\t\t- Mesto: " + obavestenje.getTelo().getUvid().getAdresa().getMesto());
        System.out.println("\t\t\t- Ulica: " + obavestenje.getTelo().getUvid().getAdresa().getUlica());
        System.out.println("\t\t\t- Broj: " + obavestenje.getTelo().getUvid().getAdresa().getBroj());
        System.out.println("\t\t\t- Broj kancelarije: " + obavestenje.getTelo().getUvid().getAdresa().getBrKancelarije());

        System.out.println("\t- Ukupan trošak: " + obavestenje.getTelo().getTrosak() + " din.");
    }

    public void ispisiMeni() {
        System.out.println("********** RAD SA OBAVEŠTENJEM **********");
        System.out.println("1) Prikaži obaveštenje");
        System.out.println("2) Prikaži opis tražene informacije");
        System.out.println("3) Promeni ukupan trošak");
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
                    ispisiObavestenje();
                    break;
                case "2":
                    ispisiInformacije();
                    break;
                case "3":
                    System.out.print("Unesite novi ukupan trošak: ");
                    try {
                        Double noviTrosak = null;
                        noviTrosak = scanner.nextDouble();
                        scanner.nextLine();
                        promeniUkupanTrosak(noviTrosak.toString());
                    } catch (Exception ex) {
                        System.out.println("Niste uneli dobru vrednost.");
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
        ObradaObavestenje obradaObavestenje = new ObradaObavestenje();
        obradaObavestenje.pokreni();
    }
}
