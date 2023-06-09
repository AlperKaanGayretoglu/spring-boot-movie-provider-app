package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.model.response.UserResponse;
import com.alpergayretoglu.movie_provider.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/{userId}")
@RequiredArgsConstructor
public class UserInterestController {

    private final UserService userService;

    @PostMapping("/movie/{movieId}/favorite")
    public UserResponse favoriteMovie(@PathVariable String userId, @PathVariable String movieId) {
        return UserResponse.fromEntity(userService.favoriteMovie(userId, movieId));
    }

    @PostMapping("/movie/{movieId}/unfavorite")
    public UserResponse unfavoriteMovie(@PathVariable String userId, @PathVariable String movieId) {
        return UserResponse.fromEntity(userService.unfavoriteMovie(userId, movieId));
    }

    @PostMapping("/category/{categoryId}/follow")
    public UserResponse followCategory(@PathVariable String userId, @PathVariable String categoryId) {
        return UserResponse.fromEntity(userService.followCategory(userId, categoryId));
    }

    @PostMapping("/category/{categoryId}/unfollow")
    public UserResponse unfollowCategory(@PathVariable String userId, @PathVariable String categoryId) {
        return UserResponse.fromEntity(userService.unfollowCategory(userId, categoryId));
    }
}