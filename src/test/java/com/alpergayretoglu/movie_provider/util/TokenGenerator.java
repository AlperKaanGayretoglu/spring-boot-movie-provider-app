package com.alpergayretoglu.movie_provider.util;

import com.alpergayretoglu.movie_provider.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenGenerator {

    public final TestRestTemplate restTemplate;
    public final JwtService jwtService;

    public HttpHeaders generateJwtHeader(String username) {
        String token = jwtService.generateToken(username);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return headers;
    }
}
