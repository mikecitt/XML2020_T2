package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.ObavestenjeService;
import com.administration.services.business.ZahtevService;
import com.administration.services.enums.StatusZahteva;
import com.administration.services.model.Korisnik;
import com.administration.services.model.Obavestenja;
import com.administration.services.model.Obavestenje;
import com.administration.services.model.Zahtev;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/obavestenje")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private ZahtevService zahtevService;

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @GetMapping("/all")
    public ResponseEntity<Obavestenja> getAllObavestenja() {
        Obavestenja obavestenja = null;

        try {
            obavestenja = obavestenjeService.getAllObavestenja();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(obavestenja, HttpStatus.OK);
    }

    @GetMapping("/byZahtev/pdf")
    public ResponseEntity<byte[]> getZahtevObavestenjePDF(@RequestParam String zahtevId) {
        Obavestenje obavestenje = null;

        try {
            obavestenje = obavestenjeService.getZahtevObavestenje(zahtevId);
            if (obavestenje == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            Zahtev z = zahtevService.getZahtev(zahtevId);
            if (k.getTipKorisnika().equals("ROLE_GRADJANIN") && z != null
                    && !z.getInformacijeOTraziocu().getHref().equals(k.getAbout()))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(obavestenjeService.getObavestenjePDF(obavestenje), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Obavestenje> getObavestenje(@RequestParam String obavestenjeId) {
        Obavestenje obavestenje = null;

        try {
            obavestenje = obavestenjeService.getObavestenje(obavestenjeId);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            if (k.getTipKorisnika().equals("ROLE_GRADJANIN") && obavestenje != null) {
                String href = (obavestenje.getTelo().getTrazenaInformacija().getHref());
                Zahtev z = zahtevService.getZahtev(href.substring(href.lastIndexOf('/') + 1));
                if (z == null || !z.getInformacijeOTraziocu().getHref().equals(k.getAbout()))
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(obavestenje, HttpStatus.OK);
    }

    @GetMapping("/byZahtev")
    public ResponseEntity<Obavestenje> getZahtevObavestenje(@RequestParam String zahtevId) {
        Obavestenje obavestenje = null;

        try {
            obavestenje = obavestenjeService.getZahtevObavestenje(zahtevId);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Korisnik k = korisnikService.getKorisnikByEmail(authentication.getName());
            Zahtev z = zahtevService.getZahtev(zahtevId);
            if (k.getTipKorisnika().equals("ROLE_GRADJANIN") && z != null
                    && !z.getInformacijeOTraziocu().getHref().equals(k.getAbout()))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(obavestenje, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PostMapping
    public ResponseEntity<?> addNewObavestenje(@RequestParam String zahtevId, @RequestBody Obavestenje obavestenje) {
        try {
            if (obavestenjeService.getZahtevObavestenje(zahtevId) != null)
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            obavestenjeService.addNewObavestenje(zahtevId, obavestenje);
            zahtevService.resolveZahtev(zahtevId, StatusZahteva.PRIHVACEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(obavestenje, HttpStatus.OK);
    }
}
