package com.administration.services.controller;

import com.administration.services.business.ZahtevService;
import com.administration.services.model.Zahtev;
import com.administration.services.model.Zahtevi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/zahtevi")
public class ZahtevController {

    @Autowired
    private ZahtevService zahtevService;

    @GetMapping
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
