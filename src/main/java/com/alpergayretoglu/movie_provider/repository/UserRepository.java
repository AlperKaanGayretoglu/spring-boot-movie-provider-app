package com.alpergayretoglu.movie_provider.repository;

import com.alpergayretoglu.movie_provider.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // TODO: Can this return "Optional<User>" or does it have to be "User" ???
    public Optional<User> findByEmail(String email);

    public Optional<User> findByVerificationCode(String verificationCode);

    public Optional<User> findByRecoveryCode(String recoveryCode);

    public boolean existsByEmail(String email); // TODO: Check if this works !!!

}