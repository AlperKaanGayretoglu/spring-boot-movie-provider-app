package com.alpergayretoglu.movie_provider.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private int monthlyFee;

    private int duration; // TODO: In days ???

    private boolean isActive;

    // TODO: Do we need to specify with which table ???
    @OneToOne
    private User user; // TODO: ER says "String user_id" but idk ???

    private ZonedDateTime createdDate;
}
