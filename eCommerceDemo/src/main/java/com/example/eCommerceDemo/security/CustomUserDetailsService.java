package com.example.eCommerceDemo.security;

import com.example.eCommerceDemo.exceptions.UserNameNotFoundException;
import com.example.eCommerceDemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService {

    /*private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByEmail (String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)

    }*/
}
