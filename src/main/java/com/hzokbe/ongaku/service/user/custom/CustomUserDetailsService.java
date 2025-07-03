package com.hzokbe.ongaku.service.user.custom;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.repository.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = repository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("username not found");
        }

        var user = optionalUser.get();

        return User
            .builder()
            .username(username)
            .password(user.getPasswordHash())
            .roles(user.getRole().name())
            .build();
    }
}
