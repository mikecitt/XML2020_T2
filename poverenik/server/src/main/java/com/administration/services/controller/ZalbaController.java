package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.ZalbaService;
import com.administration.services.enums.TipKorisnika;
import com.administration.services.model.*;
import com.administration.services.ws.client.ZalbaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/zalbe")
public class ZalbaController {

    @Autowired
    private ZalbaService zalbaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private ZalbaClient zalbaClient;

    @GetMapping("/cutanje")
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
    public ResponseEntity<Zalbecutanje> getAllZalbeCutanje(Principal user) {
        Zalbecutanje zalbecutanje = null;
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik.getTipKorisnika().equals(TipKorisnika.POVERENIK.toString())) {
                zalbecutanje = zalbaService.getAllZalbeCutanje();
            } else {
                zalbecutanje = zalbaService.getKorisnikZalbeCutanje(
                        korisnik.getEmailAdresa().getValue());
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbecutanje, HttpStatus.OK);
    }

    @GetMapping("/cutanje/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
    public ResponseEntity<Zalbacutanje> getOneZalbaCutanje(@PathVariable String id, Principal user) {
        Zalbacutanje zalbacutanje = null;
        try {
            String korisnikId = null;
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik.getTipKorisnika().equals(TipKorisnika.GRADJANIN.toString())) {
                korisnikId = user.getName();
            }
            zalbacutanje = zalbaService.getZalbaCutanje(id, korisnikId);
        } catch (Exception e) {
            if(e.getMessage().equals("Zalba not belong to Korisnik"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbacutanje, HttpStatus.OK);
    }

    @PostMapping("/cutanje")
    @PreAuthorize("hasRole('ROLE_GRADJANIN')")
    public ResponseEntity<?> addNewZalbaCutanje(@RequestBody Zalbacutanje zalbacutanje, Principal user) {
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik ==  null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            zalbaService.addNewZalbaCutanje(zalbacutanje, korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbacutanje, HttpStatus.OK);
    }

    @GetMapping("/odluka")
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
    public ResponseEntity<?> getAllZalbeOdluku(Principal user) {
        Zalbenaodluku zalbenaodluku = null;
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik.getTipKorisnika().equals(TipKorisnika.POVERENIK.toString())) {
                zalbenaodluku = zalbaService.getAllZalbeOdluka();
            } else {
                zalbenaodluku = zalbaService.getKorisnikZalbaOdluku(
                        korisnik.getEmailAdresa().getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbenaodluku, HttpStatus.OK);
    }

    @GetMapping("/odluka/{id}")
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
    public ResponseEntity<Zalbanaodluku> getOneZalbaOdluka(@PathVariable String id, Principal user) {
        Zalbanaodluku zalbaodluku = null;
        try {
            String korisnikId = null;
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik.getTipKorisnika().equals(TipKorisnika.GRADJANIN.toString())) {
                korisnikId = user.getName();
            }
            zalbaodluku = zalbaService.getZalbaOdluku(id, korisnikId);
        } catch (Exception e) {
            if(e.getMessage().equals("Zalba not belong to Korisnik"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbaodluku, HttpStatus.OK);
    }

    @PostMapping("/odluka")
    @PreAuthorize("hasRole('ROLE_GRADJANIN')")
    public ResponseEntity<?> addNewZalbaOdluku(@RequestBody Zalbanaodluku zalbanaodluku, Principal user) {
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik ==  null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            zalbaService.addNewZalbaOdluka(zalbanaodluku, korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbanaodluku, HttpStatus.OK);
    }

    @GetMapping("/testwdsl")
    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    public ResponseEntity<Zalbanaodluku> checkZalbaRazlog(@RequestParam String id) {
        Zalbanaodluku zalbaodluku = null;
        try {
            zalbaodluku = zalbaService.getZalbaOdluku(id, null);
            zalbaClient.sendZalbaOdluku(zalbaodluku);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbaodluku, HttpStatus.OK);
    }
}
