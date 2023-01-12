package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockPlaceDto {
    private String placeCode;
    private String placeName;

    public LivestockPlaceDto(Livestock livestock) {
        this.placeCode = livestock.getPlaceCode();
        this.placeName = livestock.getPlaceName();
    }
}
