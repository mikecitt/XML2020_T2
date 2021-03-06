package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.ZahtevService;
import com.administration.services.enums.StatusZahteva;
import com.administration.services.model.Korisnik;
import com.administration.services.model.Zahtev;
import com.administration.services.model.Zahtevi;
import com.administration.services.ws.client.EPostaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/zahtev")
public class ZahtevController {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private EPostaClient ePostaClient;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getZahtevPDF(@RequestParam String zahtevId) {
        Zahtev zahtev = null;

        try {
            zahtev = zahtevService.getZahtev(zahtevId);
            if (zahtev == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            if (k.getTipKorisnika().equals("ROLE_GRADJANIN") && zahtev != null
                    && !zahtev.getInformacijeOTraziocu().getHref().equals(k.getAbout()))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(zahtevService.getZahtevPDF(zahtev), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/html")
    public ResponseEntity<byte[]> getZahtevHTML(@RequestParam String zahtevId) {
        Zahtev zahtev = null;

        try {
            zahtev = zahtevService.getZahtev(zahtevId);
            if (zahtev == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            if (k.getTipKorisnika().equals("ROLE_GRADJANIN") && zahtev != null
                    && !zahtev.getInformacijeOTraziocu().getHref().equals(k.getAbout()))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_HTML);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(zahtevService.getZahtevHTML(zahtev), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Zahtev> getZahtev(@RequestParam String zahtevId) {
        Zahtev zahtev = null;

        try {
            zahtev = zahtevService.getZahtev(zahtevId);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            if (k.getTipKorisnika().equals("ROLE_GRADJANIN") && zahtev != null
                    && !zahtev.getInformacijeOTraziocu().getHref().equals(k.getAbout()))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_GRADJANIN')")
    @GetMapping("/my")
    public ResponseEntity<Zahtevi> getKorisnikZahtevi() {
        Zahtevi zahtevi = null;

        try {
            zahtevi = zahtevService.getKorisnikZahtevi();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @GetMapping("/all")
    public ResponseEntity<Zahtevi> getAllZahtevi() {
        Zahtevi zahtevi = null;

        try {
            zahtevi = zahtevService.getAllZahtevi();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PutMapping("/decline")
    public ResponseEntity<Void> declineZahtev(@RequestParam String zahtevId) {
        try {
            Zahtev z = zahtevService.resolveZahtev(zahtevId, StatusZahteva.ODBIJEN);
            if (z == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            String[] splitovano = z.getInformacijeOTraziocu().getHref().split("/");
            String[] splitovano1 = z.getAbout().split("/");
            ePostaClient.sendMail("Zahtev odbijen",
                    "Vaš zahtev sa ID-jem " + splitovano1[splitovano1.length - 1] + " je odbijen.",
                    splitovano[splitovano.length - 1]);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_GRADJANIN')")
    @PostMapping
    public ResponseEntity<?> addNewZahtev(@RequestBody Zahtev zahtev) {
        try {
            zahtevService.addNewZahtev(zahtev);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zahtev, HttpStatus.OK);
    }
}
