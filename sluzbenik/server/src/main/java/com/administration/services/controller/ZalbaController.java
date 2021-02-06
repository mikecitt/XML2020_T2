package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.ZalbaService;
import com.administration.services.dto.OdgovorDTO;
import com.administration.services.model.*;
import com.administration.services.ws.client.ZalbaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<Zalbecutanje> getAllZalbeCutanje() {
        Zalbecutanje zalbecutanje = null;
        try {
            zalbecutanje = zalbaService.getAllZalbeCutanje();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(zalbecutanje, HttpStatus.OK);
    }

    @GetMapping("/cutanje/pdf/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<byte[]> getOneZalbaCutanjePDF(@PathVariable String id) {
        Zalbacutanje zalbacutanje = null;
        try {
            zalbacutanje = zalbaService.getZalbaCutanje(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(zalbaService.getZalbaCutanjePDF(zalbacutanje), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/odluka")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
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

    @GetMapping("/odluka/pdf/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<byte[]> getOneZalbaOdlukaPDF(@PathVariable String id) {
        Zalbanaodluku zalbaodluku = null;
        try {
            zalbaodluku = zalbaService.getZalbaOdluku(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(zalbaService.getZalbaOdlukuPDF(zalbaodluku), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/odluka/status/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<Void> placeStatusOdluka(@PathVariable String id, @RequestBody OdgovorDTO odgovor) {
        try {
            zalbaService.odgovoriNaZalbuOdluku(id);
            zalbaClient.sendOdgovor(odgovor.getOdgovor(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cutanje/status/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SLUZBENIK')")
    public ResponseEntity<Void> placeStatusCutanje(@PathVariable String id, @RequestBody OdgovorDTO odgovor) {
        try {
            zalbaService.odgovoriNaZalbuCutanje(id);
            zalbaClient.sendOdgovor(odgovor.getOdgovor(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
