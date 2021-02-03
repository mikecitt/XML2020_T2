package com.administration.services.controller;

import com.administration.services.business.ObavestenjeService;
import com.administration.services.model.Obavestenja;
import com.administration.services.model.Obavestenje;

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
@RequestMapping("/obavestenja")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

    @GetMapping
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

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @PostMapping
    public ResponseEntity<?> addNewObavestenje(@RequestBody Obavestenje obavestenje) {
        try {
            obavestenjeService.addNewObavestenje(obavestenje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(obavestenje, HttpStatus.OK);
    }
}
