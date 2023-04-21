package com.alpergayretoglu.movie_provider.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String url;

    private String name;

    // TODO: What is this? Is it MovieType OR FileType (I think FileType)
    private String contentType; // TODO: Should this be an enum ???

    private String byteSize;

}