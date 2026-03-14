import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

void main() {
    Javalin.create(this::registerRoutes).start(8080);
}

void registerRoutes(JavalinConfig config) {
    config.routes.get("/", context -> context.result("Hello, world!"));
}
