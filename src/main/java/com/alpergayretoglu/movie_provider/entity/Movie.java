package com.alpergayretoglu.movie_provider.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("movies")
    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    // TODO: More attributes needed about movie (example: movie_length, starring...)

}