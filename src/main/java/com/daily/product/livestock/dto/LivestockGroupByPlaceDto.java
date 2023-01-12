package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockGroupByPlaceDto {
    private String placeCode;
    private String placeName;

    public LivestockGroupByPlaceDto(Livestock livestock) {
        this.placeCode = livestock.getPlaceCode();
        this.placeName = livestock.getPlaceName();
    }
}
