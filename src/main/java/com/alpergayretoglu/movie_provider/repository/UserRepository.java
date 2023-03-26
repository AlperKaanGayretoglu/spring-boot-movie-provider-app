package com.alpergayretoglu.movie_provider.repository;

import com.alpergayretoglu.movie_provider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // TODO: Is this necessary ???
public interface UserRepository extends JpaRepository<User, String> {
    // TODO: Can this return "Optional<User>" or does it have to be "User" ???
    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email); // TODO: Check if this works !!!
    
}