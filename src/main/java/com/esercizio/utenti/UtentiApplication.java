package com.esercizio.utenti;

import com.esercizio.utenti.entity.RoleAuth;
import com.esercizio.utenti.entity.UserAuth;
import com.esercizio.utenti.service.RoleAuthService;
import com.esercizio.utenti.service.UserAuthService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Users API", version = "1.0", description = "Users Information"))
public class UtentiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtentiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
