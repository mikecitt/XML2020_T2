package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.ResenjeService;
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

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
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
                                           @RequestParam String tipZalbe,
                                           @RequestBody Resenje resenje,
                                           Principal user) {
        try {
            Korisnik korisnik = korisnikService.getKorisnikByEmail(user.getName());
            if(korisnik ==  null)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            resenjeService.addNewResenje(resenje, korisnik, tipZalbe + "/" + zalbaId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenje, HttpStatus.OK);
    }
}
