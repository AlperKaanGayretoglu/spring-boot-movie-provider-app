package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.constants.ApplicationConstants;
import com.alpergayretoglu.movie_provider.model.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getUsersTest() {
        ResponseEntity<List<UserResponse>> response = testRestTemplate.exchange( // TODO: solve the error -> URI is not absolute
                ApplicationConstants.MAIN_PATH + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).isEqualTo(userController.getUsers());
    }
}
