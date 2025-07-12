package com.FoodDeliveryApplication.service.impl;

import com.FoodDeliveryApplication.entity.User;
import com.FoodDeliveryApplication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is automatically called by Spring Security during login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUsername(username) // Or .findByEmail() if you use email for login
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),             // or user.getEmail()
                user.getPassword(),
                user.getAuthorities()             // Must return a collection of GrantedAuthority
        );
    }
}
