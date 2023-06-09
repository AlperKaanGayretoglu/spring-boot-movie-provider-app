package com.alpergayretoglu.movie_provider.model.response;

import com.alpergayretoglu.movie_provider.model.entity.ContractRecord;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractRecordResponse {
    private String id;
    private String name;
    private int monthlyFee;
    private int duration;
    private boolean isActive;

    public static ContractRecordResponse fromEntity(ContractRecord contractRecord) {
        return ContractRecordResponse.builder()
                .id(contractRecord.getId())
                .name(contractRecord.getName())
                .monthlyFee(contractRecord.getMonthlyFee())
                .duration(contractRecord.getDuration())
                .isActive(contractRecord.isActive())
                .build();
    }
}