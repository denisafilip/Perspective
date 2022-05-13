package com.example.perspective.service.security;

import com.example.perspective.model.Expert;
import com.example.perspective.repository.ExpertRepository;
import com.example.perspective.repository.UserRepository;
import com.example.perspective.model.User;
import com.example.perspective.repository.UserRepository;
import com.example.perspective.service.account.exceptions.NoSuchAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ExpertDetailsServiceImpl implements UserDetailsService {

    private final ExpertRepository expertRepository;

    @Autowired
    public ExpertDetailsServiceImpl(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, NoSuchAccountException {
        Expert expert = this.expertRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchAccountException("No expert user with the email " + email + " was found!"));
        return new org.springframework.security.core.userdetails.User(
                expert.getEmail(),
                expert.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_EXPERT")));
    }
}
