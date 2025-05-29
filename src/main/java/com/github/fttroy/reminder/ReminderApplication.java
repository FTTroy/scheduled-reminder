package com.github.fttroy.reminder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(info = @Info(title = "OpenAPI definition", version = "v0"), servers = {@Server(url = "https://dreadful-jandy-fttroy-350ad142.koyeb.app", description = "Koyeb HTTPS endpoint")})
@EnableScheduling
@SpringBootApplication
public class ReminderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReminderApplication.class, args);
    }
}
