package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.ResenjeService;
import com.administration.services.enums.TipKorisnika;
import com.administration.services.model.Korisnik;
import com.administration.services.model.Resenja;
import com.administration.services.model.Resenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/resenja")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/{resenjeId}")
    @PreAuthorize("hasAnyRole('ROLE_POVERENIK')")
    public ResponseEntity<Resenje> getOneResenje(@PathVariable String resenjeId) {
        Resenje resenje = null;
        try {
            resenje = resenjeService.getOneResenje(resenjeId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenje, HttpStatus.OK);
    }


    @GetMapping("/zalba")
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
    public ResponseEntity<Resenje> getResenjeFromZalba(@RequestParam String id, Principal user) {
        Resenje resenje = null;
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik.getTipKorisnika().equals(TipKorisnika.POVERENIK.toString())) {
                resenje = resenjeService.getOneResenjeFromZalba(id, null);
            } else {
                resenje = resenjeService.getOneResenjeFromZalba(id,
                        korisnik.getEmailAdresa().getValue());
            }
        } catch (Exception e) {
            if(e.getMessage().equals("Zalba not belong to Korisnik"))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenje, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_POVERENIK')")
    public ResponseEntity<Resenja> getAllResenja() {
        Resenja resenja = null;
        try {
            resenja = resenjeService.getAllResenja();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenja, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    public ResponseEntity<?> addNewResenje(@RequestParam String zalbaId,
                                           @RequestBody Resenje resenje,
                                           Principal user) {
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik ==  null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            resenjeService.addNewResenje(resenje, korisnik, zalbaId);
        } catch (Exception e) {
            if(e.getMessage().equals("Zalba already has Resenje"))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenje, HttpStatus.OK);
    }
}
