package com.Demointelli.demo2.Service;

import com.Demointelli.demo2.Repo.UserRepo;
import com.Demointelli.demo2.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email=authentication.getName();
        String password=authentication.getCredentials().toString();
        Users user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found"));
        if(passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getAuthority().getAuthority()));
            return new UsernamePasswordAuthenticationToken(email, password, authorities);
        }else
            throw new BadCredentialsException("invalid credentials");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
