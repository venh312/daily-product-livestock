package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockGroupByAutonomousDto {
    private String autonomousCode;
    private String autonomousName;

    public LivestockGroupByAutonomousDto(Livestock livestock) {
        this.autonomousCode = livestock.getAutonomousCode();
        this.autonomousName = livestock.getAutonomousName();
    }
}
