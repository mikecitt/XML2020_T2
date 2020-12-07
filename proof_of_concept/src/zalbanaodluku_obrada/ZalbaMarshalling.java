package zalbanaodluku_obrada;

import zalbanaodluku.Zalbanaodluku;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

public class ZalbaMarshalling {
    public void test() {
        try {
            JAXBContext context = JAXBContext.newInstance("zalbanaodluku");

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Zalbanaodluku zalbanaodluku = (Zalbanaodluku) unmarshaller.unmarshal(new File("..\\docs\\instances\\zalbanaodlukucir.xml"));

            System.out.print("- Unesite novi naziv organa: ");
            Scanner scanner = new Scanner(System.in);
            zalbanaodluku.setNazivOrgana(scanner.nextLine());

            System.out.print("Uspesno ste izmenili naziv organa. Izmene ce biti sacuvane.");

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(zalbanaodluku, new File("..\\docs\\instances\\zalbanaodlukucir.xml"));
            //ispisiZalbu(zalbanaodluku);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZalbaMarshalling zalbaMarshalling = new ZalbaMarshalling();
        zalbaMarshalling.test();
    }
}
