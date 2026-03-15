package com.hzokbe.ongaku.middleware.auth;

import com.hzokbe.ongaku.service.jwt.JWTService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import org.jetbrains.annotations.NotNull;

public class AuthMiddleware implements Handler {
    private final JWTService jwtService = new JWTService();
    
    @Override
    public void handle(@NotNull Context context) {
        var path = context.path();

        if (path.equals("/sign-up") || path.equals("/sign-in")) {
            return;
        }

        var authorization = context.header("Authorization");

        if (authorization == null) {
            throw new UnauthorizedResponse("authorization header is missing");
        }

        var jwt = authorization.replace("Bearer ", "");

        jwtService.verify(jwt);
    }
}
