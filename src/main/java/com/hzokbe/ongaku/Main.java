import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hzokbe.ongaku.dto.auth.SignInRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignUpRequestDTO;
import com.hzokbe.ongaku.service.auth.AuthService;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.UnauthorizedResponse;

import static io.javalin.http.HttpStatus.CREATED;
import static io.javalin.http.HttpStatus.OK;

AuthService authService = new AuthService();

void main() {
    Javalin.create(this::registerRoutes).start(8080);
}

void registerRoutes(JavalinConfig config) {
    config.routes.get("/", context -> context.result("Hello, world!"));

    config.routes.post("/sign-up", context -> {
        var dto = context.bodyAsClass(SignUpRequestDTO.class);

        context.status(CREATED).json(authService.signUp(dto));
    });

    config.routes.post("/sign-in", context -> {
        var dto = context.bodyAsClass(SignInRequestDTO.class);

        context.status(OK).json(authService.signIn(dto));
    });

    config.routes.before(context -> {
        var path = context.path();

        if (path.equals("/sign-up") || path.equals("/sign-in")) {
            return;
        }

        var authorization = context.header("Authorization");

        if (authorization == null) {
            throw new UnauthorizedResponse("authorization header is missing");
        }

        var jwt = authorization.replace("Bearer ", "");

        var jwtSecret = getJWTSecret();

        var verifier = JWT
                .require(Algorithm.HMAC256(jwtSecret))
                .withIssuer("ongaku")
                .build();

        try {
            verifier.verify(jwt);
        } catch (JWTVerificationException exception) {
            throw new UnauthorizedResponse("JWT is invalid or expired");
        }
    });
}

String getJWTSecret() {
    var jwtSecret = System.getenv("JWT_SECRET");

    if (jwtSecret == null) {
        throw new InternalServerErrorResponse("JWT secret is not defined");
    }

    return jwtSecret;
}
