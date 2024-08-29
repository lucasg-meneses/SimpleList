package com.lucasgmeneses.simplelist.api.service.auth;

import com.lucasgmeneses.simplelist.api.model.auth.UserModel;
import com.lucasgmeneses.simplelist.api.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
