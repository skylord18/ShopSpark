package com.shopspark.ShopSpark.securityconfig;

import com.shopspark.ShopSpark.entity.user.person;
import com.shopspark.ShopSpark.entity.user.roles;

import com.shopspark.ShopSpark.repository.user.personrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class usernamepasswordAuthProvider implements AuthenticationProvider {
    @Autowired
    personrepository personrepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        person person = personrepository.findByEmail(name);
        if(null!=person && person.getId()>0 &&
                passwordEncoder.matches(password, person.getPassword())){
            return new UsernamePasswordAuthenticationToken(person.getEmail(), null,getGrantedAuthorities(person.getRoles()));
        }else {
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }
    private List<GrantedAuthority> getGrantedAuthorities(roles role){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename()));
        return grantedAuthorities;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
