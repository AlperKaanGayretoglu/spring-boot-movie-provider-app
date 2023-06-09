package com.alpergayretoglu.movie_provider.model.response;

import com.alpergayretoglu.movie_provider.model.entity.Category;
import com.alpergayretoglu.movie_provider.model.entity.Movie;
import com.alpergayretoglu.movie_provider.model.entity.User;
import com.alpergayretoglu.movie_provider.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String surname;
    private String email;
    private UserRole role;
    private boolean verified;

    private List<String> followedCategories;
    private List<String> favoriteMovies;

    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole())
                .verified(user.isVerified())
                .followedCategories(user.getFollowedCategories().stream().map(Category::getName).toList())
                .favoriteMovies(user.getFavoriteMovies().stream().map(Movie::getTitle).toList())
                .build();
    }
}