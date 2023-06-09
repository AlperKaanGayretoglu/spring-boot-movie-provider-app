package com.alpergayretoglu.movie_provider.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends BaseEntity {

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