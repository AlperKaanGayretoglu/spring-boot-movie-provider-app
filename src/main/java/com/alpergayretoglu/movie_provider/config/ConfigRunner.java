package com.alpergayretoglu.movie_provider.config;

import com.alpergayretoglu.movie_provider.entity.User;
import com.alpergayretoglu.movie_provider.entity.enums.UserRole;
import com.alpergayretoglu.movie_provider.model.request.auth.RegisterRequest;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import com.alpergayretoglu.movie_provider.service.AuthenticationService;
import com.alpergayretoglu.movie_provider.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRunner implements CommandLineRunner {

    private UserService userService;
    private UserRepository userRepository;
    private AuthenticationService authenticationService;

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest regRest1 = RegisterRequest.builder()
                .name("Alper")
                .email("alpergayretoglu@gmail.com")
                .password("123456789")
                .build();

        authenticationService.register(regRest1);

        User user1 = userRepository.findByEmail(regRest1.getEmail()).orElseThrow(() -> {
            throw new RuntimeException("IN CONFIG RUNNER: No such user exists"); // TODO: make an exception for this
        });

        userService.updateUser(user1.getId(), User.builder()
                .name(user1.getName())
                .email(user1.getEmail())
                .password(user1.getPassword())
                .role(UserRole.ADMIN)
                .build());
    }
}