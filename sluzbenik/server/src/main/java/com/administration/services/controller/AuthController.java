package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.dto.UserLoginDTO;
import com.administration.services.dto.UserTokenStateDTO;
import com.administration.services.model.Korisnik;
import com.administration.services.model.KorisnikDetail;
import com.administration.services.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
            HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            KorisnikDetail korisnikDetail = (KorisnikDetail) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(korisnikDetail.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Korisnik userRequest, HttpServletRequest request) {
        if (korisnikService.getKorisnikByEmail(userRequest.getEmailAdresa().getValue()) != null) // temporary, should
            // optimize
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        Korisnik existUser;
        try {
            existUser = korisnikService.addNewKorisnik(userRequest);
            if (existUser == null)
                throw new Exception("Crash");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
