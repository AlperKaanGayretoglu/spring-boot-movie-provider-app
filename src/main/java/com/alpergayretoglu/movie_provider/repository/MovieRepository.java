package com.alpergayretoglu.movie_provider.repository;

import com.alpergayretoglu.movie_provider.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // TODO: Is this necessary ???
public interface MovieRepository extends JpaRepository<Movie, String> {

}