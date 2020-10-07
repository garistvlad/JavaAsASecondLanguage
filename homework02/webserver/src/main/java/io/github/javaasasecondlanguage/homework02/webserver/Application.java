package io.github.javaasasecondlanguage.homework02.webserver;

import io.github.javaasasecondlanguage.homework02.di.Context;
import io.github.javaasasecondlanguage.homework02.di.Injector;

import java.util.Map;
import java.util.concurrent.Executors;



public class Application {
    public static void initDI() {
        new Context()
                .register(8080, "port")
                .register("localhost", "host")
                .register("Hello dear ", "welcomeText")
                .register(Logger.LogLevel.ERROR, "currentLogLevel")
                .register(Logger.LogLevel.INFO, "httpRequestLogLevel")
                .register(Logger.LogLevel.ERROR, "httpErrorLogLevel")
                .register((Logger) new CustomLogger())
                .register(Map.of("/test", new MyHttpHandler()))
                .register(Executors.newFixedThreadPool(10))
                .register(new MyWebServer());
    }

    public static void main(String[] args) {
        initDI();
        var server = Injector.inject(WebServer.class);
        server.start();
    }
}