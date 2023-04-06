package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.constants.ApplicationConstants;
import com.alpergayretoglu.movie_provider.model.request.user.UserCreateRequest;
import com.alpergayretoglu.movie_provider.model.request.user.UserUpdateRequest;
import com.alpergayretoglu.movie_provider.model.response.UserResponse;
import com.alpergayretoglu.movie_provider.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApplicationConstants.MAIN_PATH + "/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers().stream().map(UserResponse::from).toList();
    }

    @GetMapping("{userId}")
    public UserResponse getUser(@PathVariable String userId) {
        return UserResponse.from(userService.getUser(userId));
    }

    @PostMapping
    public UserResponse addUser(@Valid @RequestBody UserCreateRequest request) {
        return UserResponse.from(userService.addUser(request));
    }

    @PutMapping("{userId}")
    public UserResponse updateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateRequest request) {
        return UserResponse.from(userService.updateUser(userId, request));
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
