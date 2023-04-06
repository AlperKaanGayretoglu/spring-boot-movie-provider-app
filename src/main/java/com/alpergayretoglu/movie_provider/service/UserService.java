package com.alpergayretoglu.movie_provider.service;

import com.alpergayretoglu.movie_provider.entity.User;
import com.alpergayretoglu.movie_provider.model.request.user.UserCreateRequest;
import com.alpergayretoglu.movie_provider.model.request.user.UserUpdateRequest;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists"); // TODO make specific exception
        }

        return userRepository.save(User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build()
        );
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return getUserWithException(id);
    }


    public User updateUser(String id, UserUpdateRequest request) {
        User oldUser = getUserWithException(id);

        oldUser.setName(request.getName());
        oldUser.setSurname(request.getSurname());
        oldUser.setEmail(request.getEmail());
        oldUser.setPassword(request.getPassword());
        oldUser.setRole(request.getRole());

        return userRepository.save(oldUser);
    }

    public void deleteUser(String userId) {
        User user = getUserWithException(userId);
        userRepository.delete(user);
    }

    private User getUserWithException(String id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("User not found"); // TODO: Make a custom exception for this
        });
    }
}