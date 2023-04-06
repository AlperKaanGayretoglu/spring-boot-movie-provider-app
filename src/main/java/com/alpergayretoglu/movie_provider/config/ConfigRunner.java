package com.alpergayretoglu.movie_provider.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRunner implements CommandLineRunner {

    /*
    private UserService userService;
    private UserRepository userRepository;
    private AuthenticationService authenticationService;
     */

    @Override
    public void run(String... args) throws Exception {
        /* // Instead of this, we have data.sql now!
        RegisterRequest regRest1 = RegisterRequest.builder()
                .name("Alper")
                .surname("Gayretoğlu")
                .email("alpergayretoglu@gmail.com")
                .password("123456789")
                .build();

        authenticationService.register(regRest1);

        User user1 = userRepository.findByEmail(regRest1.getEmail()).orElseThrow(() -> {
            throw new RuntimeException("IN CONFIG RUNNER: No such user exists"); // TODO: make an exception for this
        });

        userService.updateUser(user1.getId(), UserUpdateRequest.builder()
                .name(user1.getName())
                .surname(user1.getSurname())
                .email(user1.getEmail())
                .password(user1.getPassword())
                .role(UserRole.ADMIN)
                .build());
        */
    }


}