package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockProductInfoDto {
    private String placeCode;
    private String placeName;
    private String productCode;
    private String productName;
    private String standard;
    private Long price;
    private String remarks;
    private String autonomousCode;
    private String autonomousName;
    private String checkDate;

    public LivestockProductInfoDto(Livestock livestock) {
        this.placeCode = livestock.getPlaceCode();
        this.placeName = livestock.getPlaceName();
        this.productCode = livestock.getProductCode();
        this.productName = livestock.getProductName();
        this.standard = livestock.getStandard();
        this.price = livestock.getPrice();
        this.remarks = livestock.getRemarks();
        this.autonomousCode = livestock.getAutonomousCode();
        this.autonomousName = livestock.getAutonomousName();
        this.checkDate = livestock.getCheckDate();
    }
}
