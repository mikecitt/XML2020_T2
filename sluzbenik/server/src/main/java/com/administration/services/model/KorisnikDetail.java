package com.administration.services.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class KorisnikDetail extends Korisnik implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(new Authority(tipKorisnika));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return sifra;
    }

    @Override
    public String getUsername() {
        return emailAdresa.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static KorisnikDetail createDetail(Korisnik korisnik) {
        if (korisnik == null) {
            return null;
        }

        KorisnikDetail k = new KorisnikDetail();
        k.setEmailAdresa(korisnik.getEmailAdresa());
        k.setSifra(korisnik.getSifra());
        k.setIme(korisnik.getIme());
        k.setPrezime(korisnik.getPrezime());
        k.setDatumPromeneSifre(korisnik.getDatumPromeneSifre());
        k.setTipKorisnika(korisnik.getTipKorisnika());

        return k;
    }
}
