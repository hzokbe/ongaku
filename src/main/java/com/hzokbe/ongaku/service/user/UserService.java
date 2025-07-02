package com.hzokbe.ongaku.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hzokbe.ongaku.exception.user.AlreadyRegisteredUserException;
import com.hzokbe.ongaku.exception.user.InvalidPasswordException;
import com.hzokbe.ongaku.exception.user.InvalidUsernameException;
import com.hzokbe.ongaku.model.user.User;
import com.hzokbe.ongaku.model.user.request.UserRequest;
import com.hzokbe.ongaku.model.user.response.UserResponse;
import com.hzokbe.ongaku.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;

        this.encoder = encoder;
    }

    public UserResponse signUp(UserRequest request) {
        var username = request.getUsername();

        if (username == null) {
            throw new InvalidUsernameException("username cannot be null");
        }

        username = username.trim();

        if (username.isBlank()) {
            throw new InvalidUsernameException("username cannot be blank");
        }

        if (!username.matches("^[A-Za-z0-9_]+$")) {
            throw new InvalidUsernameException("uername must contain only letters, numbers, and underscores");
        }

        if (repository.existsByUsername(username)) {
            throw new AlreadyRegisteredUserException("already registered user");
        }

        var rawPassword = request.getRawPassword();

        if (rawPassword == null) {
            throw new InvalidPasswordException("password cannot be null");
        }

        if (rawPassword.isBlank()) {
            throw new InvalidPasswordException("password cannot be blank");
        }

        if (rawPassword.length() < 8) {
            throw new InvalidPasswordException("password must be at least 8 characters long");
        }

        var user = new User(username, encoder.encode(rawPassword));

        user = repository.save(user);

        return new UserResponse(user.getId(), username);
    }
}
