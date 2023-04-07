package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.model.entity.User;
import com.alpergayretoglu.movie_provider.model.request.auth.AuthenticationRequest;
import com.alpergayretoglu.movie_provider.model.response.AuthenticationResponse;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void given_local_email_and_password_when_login_then_status_code_is_200_and_it_should_return_id_token() {
        //Given
        String mailAddress = "alperkaangayretoglu@gmail.com";
        String password = "123456789";

        AuthenticationRequest AuthenticationRequest = new AuthenticationRequest();
        AuthenticationRequest.setUsername(mailAddress);
        AuthenticationRequest.setPassword(password);

        JsonNode requestBodyJson = objectMapper.valueToTree(AuthenticationRequest);

        User adminUser = userRepository.findByEmail(mailAddress).orElseThrow(RuntimeException::new);

        //When
        final HttpEntity<JsonNode> request = new HttpEntity<>(requestBodyJson, new HttpHeaders());
        final ResponseEntity<AuthenticationResponse> response = restTemplate.exchange("/auth/login", HttpMethod.POST,
                request, new ParameterizedTypeReference<>() {
                });

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // assertEquals(adminUser.getId(), response.getBody().getId()); // TODO: Should we carry Id with AuthenticationResponse?
        //TODO: find a way to validate JWT token
    }
}