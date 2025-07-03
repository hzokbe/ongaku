package com.hzokbe.ongaku.service.user.custom;

import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.repository.user.UserRepository;

@Service
public class CustomUserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
}
