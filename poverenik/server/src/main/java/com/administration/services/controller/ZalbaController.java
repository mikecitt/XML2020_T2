package com.administration.services.controller;

import com.administration.services.business.ZalbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/zalbe")
public class ZalbaController {

    @Autowired
    private ZalbaService zalbaService;

    @GetMapping("/cutanje")
    public ResponseEntity<?> getAllZalbeCutanje()
    {
        try {
            zalbaService.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cutanje")
    public ResponseEntity<?> addNewZalbaCutanje()
    {
        try {
            zalbaService.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/odluka")
    public ResponseEntity<?> getAllZalbeOdluku()
    {
        try {
            zalbaService.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/odluka")
    public ResponseEntity<?> addNewZalbaOdluku()
    {
        try {
            zalbaService.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
