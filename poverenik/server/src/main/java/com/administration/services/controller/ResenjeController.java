package com.administration.services.controller;

import com.administration.services.business.ResenjeService;
import com.administration.services.model.Resenja;
import com.administration.services.model.Resenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resenja")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_GRADJANIN', 'ROLE_POVERENIK')")
    public ResponseEntity<Resenja> getAllResenja() {
        Resenja resenja = null;
        try {
            resenja = resenjeService.getAllResenja();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenja, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_POVERENIK')")
    public ResponseEntity<?> addNewResenje(@RequestBody Resenje resenje) {
        try {
            resenjeService.addNewResenje(resenje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resenje, HttpStatus.OK);
    }
}
