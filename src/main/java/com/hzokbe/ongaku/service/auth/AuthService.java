package com.hzokbe.ongaku.service.auth;

import com.hzokbe.ongaku.dto.auth.SignInRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignInResponseDTO;
import com.hzokbe.ongaku.dto.auth.SignUpRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignUpResponseDTO;
import com.hzokbe.ongaku.model.user.User;
import com.hzokbe.ongaku.repository.user.UserRepository;
import com.hzokbe.ongaku.service.jwt.JWTService;
import com.password4j.Password;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.UnauthorizedResponse;

import java.util.UUID;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    private final JWTService jwtService = new JWTService();

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        var username = verifyUsername(dto.username());

        var password = verifyPassword(dto.password());

        var passwordHash = hash(password);

        var user = new User(UUID.randomUUID(), username, passwordHash);

        user = userRepository.create(user);

        return new SignUpResponseDTO(user.id(), user.username());
    }

    public SignInResponseDTO signIn(SignInRequestDTO dto) {
        var username = dto.username();

        var optionalUser = userRepository.getByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UnauthorizedResponse("invalid username or password");
        }

        var user = optionalUser.get();

        var password = dto.password();

        var passwordHash = user.passwordHash();

        if (!Password.check(password, passwordHash).withArgon2()) {
            throw new UnauthorizedResponse("invalid username or password");
        }

        return new SignInResponseDTO(jwtService.generate(username));
    }

    public String verifyUsername(String username) {
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

        return username;
    }

    public String verifyPassword(String password) {
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

        return password;
    }

    public String hash(String password) {
        return Password.hash(password).withArgon2().getResult();
    }
}
