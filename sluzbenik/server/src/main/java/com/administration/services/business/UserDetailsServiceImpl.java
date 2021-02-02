package com.administration.services.business;

import com.administration.services.model.KorisnikDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KorisnikDetail user = KorisnikDetail.createDetail(korisnikService.getKorisnikByEmail(username));
        if (user == null)
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

        return user;
    }
}
