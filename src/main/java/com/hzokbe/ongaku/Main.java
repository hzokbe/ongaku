import com.hzokbe.ongaku.dto.auth.SignInRequestDTO;
import com.hzokbe.ongaku.dto.auth.SignUpRequestDTO;
import com.hzokbe.ongaku.middleware.auth.AuthMiddleware;
import com.hzokbe.ongaku.service.auth.AuthService;
import com.hzokbe.ongaku.service.song.SongService;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

import static io.javalin.http.HttpStatus.CREATED;
import static io.javalin.http.HttpStatus.OK;

AuthService authService = new AuthService();

SongService songService = new SongService();

AuthMiddleware authMiddleware = new AuthMiddleware();

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

    config.routes.before(authMiddleware);
}
