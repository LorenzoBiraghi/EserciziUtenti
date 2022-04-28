package com.esercizio.utenti;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Users API", version = "1.0", description = "Users Information"))
public class UtentiApplication {public static void main(String[] args) {SpringApplication.run(UtentiApplication.class, args);}}
