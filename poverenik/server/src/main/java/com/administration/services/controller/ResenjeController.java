package com.administration.services.controller;

import com.administration.services.business.ResenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resenja")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @GetMapping
    public ResponseEntity<?> getAllResenja()
    {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewResenje()
    {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
