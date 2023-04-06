package com.alpergayretoglu.movie_provider.repository;

import com.alpergayretoglu.movie_provider.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // TODO: Is this necessary ???
public interface CategoryRepository extends JpaRepository<Category, String> {

    public Optional<Category> findCategoryByName(String name);

}