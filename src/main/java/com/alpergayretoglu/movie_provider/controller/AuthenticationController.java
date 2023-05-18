package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.model.request.auth.AuthenticationRequest;
import com.alpergayretoglu.movie_provider.model.request.auth.RegisterRequest;
import com.alpergayretoglu.movie_provider.model.response.AuthenticationResponse;
import com.alpergayretoglu.movie_provider.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.login(request);
    }

    @GetMapping("/verify") // TODO: GET or POST ?
    public void verify(@RequestParam String code) {
        authenticationService.verify(code);
    }

    @GetMapping("/verify/email")
    // TODO email service is later, for now it just sends HTTP response
    public String sendVerificationCodeToEmail(@RequestParam String email) {
        String code = authenticationService.sendVerificationCodeToEmail(email);
        return "Your verification link is http://localhost:8080/verify?code=" + code;
    }


    @GetMapping("/recover")
    // sends a new random generated password
    public String recoverPassword(@RequestParam String code) {
        String newPassword = authenticationService.recoverPasswordByGeneratingNew(code);
        return "Your new password is " + newPassword;
    }

    @GetMapping("/recover/email/{userId}")
    // TODO email service is later, for now it just sends HTTP response
    public String sendRecoveryCodeToEmail(@PathVariable String userId) {
        String code = authenticationService.createRecoveryCode(userId);
        return "Your password recovery link is http://localhost:8080/recover?code=" + code;
    }

}