package com.administration.services.controller;

import com.administration.services.business.KorisnikService;
import com.administration.services.business.UserDetailsServiceImpl;
import com.administration.services.dto.UserLoginDTO;
import com.administration.services.dto.UserTokenStateDTO;
import com.administration.services.helpers.XSLFOTransformer;
import com.administration.services.model.Korisnik;
import com.administration.services.model.KorisnikDetail;
import com.administration.services.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private XSLFOTransformer xslfoTransformer;

    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
                                                                       HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            KorisnikDetail korisnikDetail = (KorisnikDetail) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(korisnikDetail.getUsername());
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, korisnikDetail.getTipKorisnika()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Korisnik userRequest,
                                     HttpServletRequest request) {
        Korisnik existUser;
        try {
            existUser = korisnikService.addNewKorisnik(userRequest);
        } catch (Exception e) {
            if(e.getMessage().equals("User exist")) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/xsl")
    public ResponseEntity<?> registerUser() {
        try {
            xslfoTransformer.generatePDF();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/getpdf", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Here you have to set the actual filename of your pdf
            String filename = "test.pdf";
            //headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(xslfoTransformer.generatePDF(), headers, HttpStatus.OK);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
