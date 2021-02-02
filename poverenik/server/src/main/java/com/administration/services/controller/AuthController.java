package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/{email}")
    public ResponseEntity<Korisnik> getKorisnik(@PathVariable String email) {
        Korisnik korisnikDetail = null;

        try {
            korisnikDetail = korisnikService.getKorisnikByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(korisnikDetail, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewKorisnik(@RequestBody Korisnik korisnik) {
        try {
            korisnikService.addNewKorisnik(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }
}
