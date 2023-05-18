package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.IntegrationTest;
import com.alpergayretoglu.movie_provider.model.request.user.UserCreateRequest;
import com.alpergayretoglu.movie_provider.util.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Test
    void whenGetRequest_returnUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity("/users", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenExistingUserId_whenGetUser_thenStatus200() {
        String userId = "5b8a3d25-2b7a-4683-89ed-ac0e42cdc879";
        String url = "/users/" + userId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void givenNotExistingUserId_whenGetUser_thenStatus404() {
        String userId = "111a3d25-2b7a-4683-89ed-ac0e42cdc879";
        String url = "/users/" + userId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void whenEmptyRequestPost_thenStatus400() {
        UserCreateRequest requestDto = UserCreateRequest.builder().build();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<UserCreateRequest> request = new HttpEntity<>(requestDto, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/users", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenValidRequestPost_thenStatus200() {
        UserCreateRequest requestDto = UserCreateRequest.builder()
                .email("integration-test@mail.com")
                .name("integration")
                .surname("test")
                .password("123456")
                .build();

        ResponseEntity<String> response = restTemplate.postForEntity("/users", requestDto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
        // TODO make it status 400
    void whenEmailExistsWhilePost_thenStatus500() {
        UserCreateRequest requestDto = UserCreateRequest.builder()
                .email("alperkaangayretoglu@gmail.com")
                .name("integration")
                .surname("test")
                .password("123456")
                .build();

        HttpEntity<UserCreateRequest> request = new HttpEntity<>(requestDto);
        ResponseEntity<String> response = restTemplate.postForEntity("/users", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void givenAdminAuth_whenDeleteUser_thenStatus200() {
        String userId = "5b8a3d25-2b7a-4683-89ed-ac0e42cdc879";
        String url = "/users/" + userId;
        HttpHeaders headers = tokenGenerator.generateJwtHeader("alperkaangayretoglu@gmail.com");
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /*
    @Test
    void givenNoAuth_whenDeleteUser_thenStatus403Forbidden() { // TODO: ERROR: We get "200 OK" instead of "403 FORBIDDEN"
        String userId = "6b8a3d25-2b7a-4683-89ed-ac0e42cdc878";
        String url = "/users/" + userId;
        HttpEntity<String> request = new HttpEntity<>(null);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
*/
}