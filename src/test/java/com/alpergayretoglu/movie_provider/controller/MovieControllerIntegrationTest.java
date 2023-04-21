package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.IntegrationTest;
import com.alpergayretoglu.movie_provider.model.response.MovieResponse;
import com.alpergayretoglu.movie_provider.service.MovieService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional // TODO : make a repository method to get Categories of Movies
    public void givenMovieList_whenListMoviesEndpointCalled_thenRetrievesMovieList() {
        // Given
        List<MovieResponse> expectedMovieList = movieService.listMovies();

        // When
        ResponseEntity<List<MovieResponse>> responseEntity = restTemplate.exchange(
                "/movie",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<MovieResponse> actualMovieList = responseEntity.getBody();
        assertThat(actualMovieList).isEqualTo(expectedMovieList);
    }
}