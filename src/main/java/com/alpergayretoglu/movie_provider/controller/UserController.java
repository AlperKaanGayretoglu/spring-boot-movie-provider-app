package com.alpergayretoglu.movie_provider.controller;

import com.alpergayretoglu.movie_provider.entity.User;
import com.alpergayretoglu.movie_provider.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers(); // TODO: Make this a UserResponse later
    }
}
