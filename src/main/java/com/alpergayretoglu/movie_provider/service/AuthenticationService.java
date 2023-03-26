package com.alpergayretoglu.movie_provider.service;

import com.alpergayretoglu.movie_provider.entity.User;
import com.alpergayretoglu.movie_provider.entity.enums.UserRole;
import com.alpergayretoglu.movie_provider.model.request.auth.AuthenticationRequest;
import com.alpergayretoglu.movie_provider.model.request.auth.RegisterRequest;
import com.alpergayretoglu.movie_provider.model.response.AuthenticationResponse;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import com.alpergayretoglu.movie_provider.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // hash password
                .role(UserRole.GUEST)
                .build();

        // check if user exists before register
        // TODO !!! SHOULDN'T THIS BE BEFORE BUILDING THE USER !!!
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists"); // TODO: specific exception
        }

        User response = userRepository.save(user);
        String jwtToken = jwtService.generateToken(response);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getUsername()).orElseThrow(() -> {
            throw new RuntimeException("Invalid Username"); // TODO: specific exception
        });

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        }

        throw new RuntimeException("Invalid Password"); // TODO specific exception
    }
}