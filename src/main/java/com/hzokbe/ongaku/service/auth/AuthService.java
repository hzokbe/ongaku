package com.hzokbe.ongaku.service.auth;

import com.hzokbe.ongaku.dto.auth.SignUpRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignUpResponseDTO;
import com.hzokbe.ongaku.model.user.User;
import com.hzokbe.ongaku.repository.user.UserRepository;
import com.password4j.Password;
import io.javalin.http.BadRequestResponse;

import java.util.UUID;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        var username = dto.username();

        if (username == null) {
            throw new BadRequestResponse("username cannot be null");
        }

        username = username.trim();

        if (username.isBlank()) {
            throw new BadRequestResponse("username cannot be blank");
        }

        if (username.length() < 8 || username.length() > 16) {
            throw new BadRequestResponse("username must be between 8 and 16 characters");
        }

        if (userRepository.existsByUsername(username)) {
            throw new BadRequestResponse("username already in use");
        }

        var password = dto.password();

        if (password == null) {
            throw new BadRequestResponse("password cannot be null");
        }

        password = password.trim();

        if (password.isBlank()) {
            throw new BadRequestResponse("password cannot be blank");
        }

        if (password.length() < 8 || password.length() > 64) {
            throw new BadRequestResponse("password must be between 8 and 64 characters");
        }

        var passwordHash = Password.hash(password).withArgon2().getResult();

        var user = new User(UUID.randomUUID(), username, passwordHash);

        user = userRepository.create(user);

        return new SignUpResponseDTO(user.id(), user.username());
    }
}
