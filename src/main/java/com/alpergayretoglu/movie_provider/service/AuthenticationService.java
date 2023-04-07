package com.alpergayretoglu.movie_provider.service;

import com.alpergayretoglu.movie_provider.exception.EntityNotFoundException;
import com.alpergayretoglu.movie_provider.model.entity.User;
import com.alpergayretoglu.movie_provider.model.enums.UserRole;
import com.alpergayretoglu.movie_provider.model.request.auth.AuthenticationRequest;
import com.alpergayretoglu.movie_provider.model.request.auth.RegisterRequest;
import com.alpergayretoglu.movie_provider.model.response.AuthenticationResponse;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import com.alpergayretoglu.movie_provider.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository; // TODO: make this userService
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

    // VERIFICATION LOGIC
    public String sendVerificationCodeToEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        return user.getVerificationCode(); // TODO : what if expired? Should create new token?
    }

    @Transactional
    public void verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode).orElseThrow(() -> {
            throw new EntityNotFoundException();
        }); // TODO : make it service method

        // check if verification code is expired
        if (user.getVerificationCodeExpireDate().isBefore(ZonedDateTime.now()))
            throw new RuntimeException("verification code is expired"); // TODO specific exception

        user.setVerified(true);
        userRepository.save(user);
    }

    // RECOVERY LOGIC
    @Transactional
    public String createRecoveryCode(String userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        String recoveryCode = UUID.randomUUID().toString();
        user.setRecoveryCode(recoveryCode);
        user.setRecoveryCodeExpiredDate(ZonedDateTime.now().plusDays(1)); // TODO: fixed value?

        userRepository.save(user);
        return recoveryCode;
    }

    @Transactional
    public String recoverPasswordByGeneratingNew(String recoveryCode) {
        User user = userRepository.findByRecoveryCode(recoveryCode).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });

        // code duplication, TODO refactor?
        ZonedDateTime expireDate = user.getRecoveryCodeExpiredDate();
        if (expireDate == null || expireDate.isBefore(ZonedDateTime.now()))
            throw new RuntimeException("verification code is expired"); // TODO specific exception

        String newPassword = UUID.randomUUID().toString().substring(0, 18);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setRecoveryCode(null); // remove code after using so that it can be used once
        user.setRecoveryCodeExpiredDate(null);
        userRepository.save(user);

        return newPassword;
    }
}