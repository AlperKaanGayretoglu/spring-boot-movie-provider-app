package com.alpergayretoglu.movie_provider.model.request.user;

import com.alpergayretoglu.movie_provider.constants.ApplicationConstants;
import com.alpergayretoglu.movie_provider.model.entity.User;
import com.alpergayretoglu.movie_provider.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = ApplicationConstants.PASSWORD_MIN_LENGTH, max = ApplicationConstants.PASSWORD_MAX_LENGTH)
    private String password;

    @NotBlank
    private UserRole role;

    public static User toEntity(UserUpdateRequest request) {
        return User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }
}