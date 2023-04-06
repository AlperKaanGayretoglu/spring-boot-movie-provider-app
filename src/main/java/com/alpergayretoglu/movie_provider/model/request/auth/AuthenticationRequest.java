package com.alpergayretoglu.movie_provider.model.request.auth;

import com.alpergayretoglu.movie_provider.constants.ApplicationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min = ApplicationConstants.PASSWORD_MIN_LENGTH, max = ApplicationConstants.PASSWORD_MAX_LENGTH)
    // TODO: Unnecessary? (since there won't be any registered passwords outside this range)
    private String password;

}