package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.model.request.user.UserCreateRequest;
import com.alpergayretoglu.movie_provider.model.request.user.UserUpdateRequest;
import com.alpergayretoglu.movie_provider.model.response.UserResponse;
import com.alpergayretoglu.movie_provider.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getUsers().stream().map(UserResponse::fromEntity).toList();
    }

    @GetMapping("{userId}")
    public UserResponse getUser(@PathVariable String userId) {
        return UserResponse.fromEntity(userService.getUser(userId));
    }

    @PostMapping
    public UserResponse addUser(@Valid @RequestBody UserCreateRequest request) {
        return UserResponse.fromEntity(userService.addUser(request));
    }

    @PutMapping("{userId}")
    public UserResponse updateUser(@PathVariable String userId, @Valid @RequestBody UserUpdateRequest request) {
        return UserResponse.fromEntity(userService.updateUser(userId, request));
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("{userId}/subscribe/{subscriptionId}")
    public void subscribe(@PathVariable String userId, @PathVariable String subscriptionId) {
        userService.subscribe(userId, subscriptionId);
    }


    // ADMIN or SELF authorization testing route, TODO: SELF DOESN'T WORK!!! (problem with SelfFilter!!!)
    /*
    @GetMapping("/admin-or-self-test/{userId}")
    public String adminOrSelfResource(@PathVariable String userId) {
        return "You have reached protected resource.";
    }
     */
}
