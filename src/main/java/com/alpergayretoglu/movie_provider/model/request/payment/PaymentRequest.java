package com.alpergayretoglu.movie_provider.model.request.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
    @NotNull
    private int amount;

    @NotBlank
    private String senderCard;

    @NotBlank
    private String receiverCard;
    
}