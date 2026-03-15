import com.hzokbe.ongaku.dto.auth.SignInRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignUpRequestDTO;
import com.hzokbe.ongaku.service.auth.AuthService;
import com.hzokbe.ongaku.service.jwt.JWTService;
import com.hzokbe.ongaku.service.song.SongService;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.UnauthorizedResponse;

import static io.javalin.http.HttpStatus.CREATED;
import static io.javalin.http.HttpStatus.OK;

AuthService authService = new AuthService();

SongService songService = new SongService();

JWTService jwtService = new JWTService();

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

    config.routes.get(
            "/songs",
            context -> context.status(OK).json(songService.getAll())
    );

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

        jwtService.verify(jwt);
    });
}
