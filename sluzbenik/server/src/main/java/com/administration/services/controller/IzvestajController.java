package com.administration.services.controller;

import com.administration.services.business.IzvestajService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @PreAuthorize("hasRole('ROLE_SLUZBENIK')")
    @GetMapping
    public ResponseEntity<?> makeIzvestaj() {
        try {
            izvestajService.makeIzvestaj();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
