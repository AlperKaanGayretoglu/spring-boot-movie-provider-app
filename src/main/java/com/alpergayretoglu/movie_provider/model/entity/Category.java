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
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    // TODO: Do we need to specify with which table ???
    @OneToOne // TODO: Is this ManyToOne OR OneToOne ???
    private Category parent;

    // TODO: Do we need to specify with which table ???
    /*
    @JoinTable(name = "category_movies", // TODO: Change the name to -> movie_categories ?
            joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    */
    @ManyToMany(fetch = FetchType.LAZY)
    // @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY) // TODO: Is this correct ???
    @JsonIgnoreProperties("categories") // TODO: Is this necessary ???
    @Builder.Default
    private Set<Movie> movies = new HashSet<>();

    private boolean isSuperCategory;

}