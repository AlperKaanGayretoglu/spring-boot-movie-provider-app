package com.alpergayretoglu.movie_provider.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int fee;

    // TODO: Do we need to specify with which table ???
    @ManyToOne
    private ContractRecord contractRecord; // TODO: ER says "String contract_record_id" but idk ???

}