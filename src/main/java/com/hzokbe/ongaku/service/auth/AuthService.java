package com.hzokbe.ongaku.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hzokbe.ongaku.dto.auth.SignInRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignInResponseDTO;
import com.hzokbe.ongaku.dto.auth.SignUpRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignUpResponseDTO;
import com.hzokbe.ongaku.model.user.User;
import com.hzokbe.ongaku.repository.user.UserRepository;
import com.password4j.Password;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.UnauthorizedResponse;

import java.time.Instant;
import java.util.UUID;

public class AuthService {
    private final UserRepository userRepository = new UserRepository();

    private final String jwtSecret;

    public AuthService() {
        var jwtSecret = System.getenv("JWT_SECRET");

        if (jwtSecret == null) {
            throw new InternalServerErrorResponse("JWT secret is not defined");
        }

        this.jwtSecret = jwtSecret;
    }

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        var username = verifyUsername(dto.username());

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

        var now = Instant.now();

        var jwt = JWT
                .create()
                .withIssuer("ongaku")
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(3_600))
                .sign(Algorithm.HMAC256(jwtSecret));

        return new SignInResponseDTO(jwt);
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
}
