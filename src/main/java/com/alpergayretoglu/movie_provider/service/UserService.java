package com.alpergayretoglu.movie_provider.service;

import com.alpergayretoglu.movie_provider.entity.User;
import com.alpergayretoglu.movie_provider.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return getUserById(id);
    }

    public void updateUser(String id, User user) {
        User oldUser = getUserById(id);

        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());

        userRepository.save(oldUser);
    }

    private User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("User not found"); // TODO: Make a custom exception for this
        });
    }
}