package com.alpergayretoglu.movie_provider.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int amount;

    private String senderCard;

    private String receiverCard;

    @ManyToOne
    private Invoice invoice;
}