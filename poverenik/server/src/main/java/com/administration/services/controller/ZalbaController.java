package com.administration.services.controller;

import com.administration.services.business.ZalbaService;
import com.administration.services.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/zalbe")
public class ZalbaController {

    @Autowired
    private ZalbaService zalbaService;

    @GetMapping("/cutanje")
    public ResponseEntity<Zalbecutanje> getAllZalbeCutanje() {
        Zalbecutanje zalbecutanje = null;
        try {
            zalbecutanje = zalbaService.getAllZalbeCutanje();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbecutanje, HttpStatus.OK);
    }

    @PostMapping("/cutanje")
    public ResponseEntity<?> addNewZalbaCutanje(@RequestBody Zalbacutanje zalbacutanje) {
        try {
            zalbaService.addNewZalbaCutanje(zalbacutanje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbacutanje, HttpStatus.OK);
    }

    @GetMapping("/odluka")
    public ResponseEntity<?> getAllZalbeOdluku() {
        Zalbenaodluku zalbenaodluku = null;
        try {
            zalbenaodluku = zalbaService.getAllZalbeOdluka();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbenaodluku, HttpStatus.OK);
    }

    @PostMapping("/odluka")
    public ResponseEntity<?> addNewZalbaOdluku(@RequestBody Zalbanaodluku zalbanaodluku) {
        try {
            zalbaService.addNewZalbaOdluka(zalbanaodluku);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbanaodluku, HttpStatus.OK);
    }
}
