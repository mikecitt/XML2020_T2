package com.administration.services.controller;

import com.administration.services.business.IzvestajService;
import com.administration.services.model.Izvestaj;
import com.administration.services.model.Izvestaji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @GetMapping("/pdf/{izvestajId}")
    @PreAuthorize("hasAnyRole('ROLE_POVERENIK')")
    public ResponseEntity<byte[]> getIzvestajPDF(@PathVariable String izvestajId) {
        Izvestaj izvestaj = null;
        try {
            izvestaj = izvestajService.getIzvestaj(izvestajId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(izvestajService.getIzvestajPDF(izvestaj), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_POVERENIK')")
    public ResponseEntity<Izvestaji> getAllIzvestaji() {
        Izvestaji izvestaji = null;
        try {
            izvestaji = izvestajService.getAllIzvestaji();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(izvestaji, HttpStatus.OK);
    }
}