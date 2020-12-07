package obrada_zalbacutanje;

import zalbacutanje.Zalbacutanje;

import java.io.File;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ZalbaCutanjeMarshalling {
    public void test() throws Exception {
        try {
            JAXBContext context = JAXBContext.newInstance("zalbacutanje");

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Zalbacutanje zalba = (Zalbacutanje) unmarshaller.unmarshal(new File("..\\docs\\instances\\zalbacutanjecir.xml"));

            System.out.print("Unesite novi naziv organa: ");
            Scanner scanner = new Scanner(System.in);
            zalba.setNazivOrgana(scanner.nextLine());

            System.out.print("Uspesno ste izmenili naziv organa. Izmene ce biti sacuvane.");

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zalba , new File("..\\docs\\instances\\zalbacutanjecir.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) throws Exception {
        ZalbaCutanjeMarshalling test = new ZalbaCutanjeMarshalling();
        test.test();
    }
}