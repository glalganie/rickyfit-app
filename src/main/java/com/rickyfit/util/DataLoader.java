package com.rickyfit.util;

import com.rickyfit.model.User;
import com.rickyfit.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            System.out.println("Database vuoto. Creo utenti demo...");

            // Utente ADMIN
            userRepository.save(new User(
                    "Ricky",
                    "Cardio",
                    "ricky@fit.it",
                    passwordEncoder.encode("Admin123"),
                    User.Role.ADMIN
            ));

            // Utente TRAINER
            userRepository.save(new User(
                    "Martina",
                    "Coach",
                    "martina@fit.it",
                    passwordEncoder.encode("Trainer123"),
                    User.Role.TRAINER
            ));

            // Utente MEMBER
            userRepository.save(new User(
                    "Glalganie",
                    "Member",
                    "glalganie@fit.it",
                    passwordEncoder.encode("Member123"),
                    User.Role.MEMBER
            ));

            System.out.println("Utenti demo creati con successo.");
        }
    }
}