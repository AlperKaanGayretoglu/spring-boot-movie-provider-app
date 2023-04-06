package com.alpergayretoglu.movie_provider.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    private final HttpStatus statusCode = HttpStatus.BAD_REQUEST;
    private String message = "The request has been rejected"; // default message

    public BadRequestException(String message) {
        this.message = message;
    }

}