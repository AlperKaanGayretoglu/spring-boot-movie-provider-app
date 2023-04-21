package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.IntegrationTest;
import com.alpergayretoglu.movie_provider.model.request.auth.RegisterRequest;
import com.alpergayretoglu.movie_provider.model.response.AuthenticationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthControllerIntegrationTest extends IntegrationTest {

    @Test
    void givenRegisterRequestWithValidEmail_thenStatus200() {
        RegisterRequest request = new RegisterRequest("Adam", "Adam",
                "adam@mail.com", "123456789");
        HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(request);

        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(
                "/auth/register", requestEntity, AuthenticationResponse.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
    }

    @Test
    void givenRegisterRequestWithInvalidEmail_thenStatus400() {
        RegisterRequest request = new RegisterRequest("Adam", "Adam",
                "adam_mail", "123456789");

        ResponseEntity<Void> response = restTemplate.postForEntity(
                "/auth/register", request, Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenRegisterRequestWithInvalidPassword_thenStatus400() {
        RegisterRequest request = new RegisterRequest("Adam", "Adam",
                "adam_mail", "123");

        ResponseEntity<Void> response = restTemplate.postForEntity(
                "/auth/register", request, Void.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}