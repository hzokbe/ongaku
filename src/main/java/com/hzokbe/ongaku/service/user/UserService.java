package com.hzokbe.ongaku.service.user;

import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
