package com.daily.product.livestock.dto;

import com.daily.product.livestock.domain.livestock.Livestock;
import lombok.Getter;

@Getter
public class LivestockResultDto {
    private Long id;
    private String placeName;
    private String productName;
    private String standard;
    private Long price;
    private String remarks;
    private String marketTypeName;
    private String autonomousName;
    private String checkDate;

    public LivestockResultDto(Livestock livestock) {
        this.id = livestock.getId();
        this.placeName = livestock.getPlaceName();
        this.productName = livestock.getProductName();
        this.standard = livestock.getStandard();
        this.price = livestock.getPrice();
        this.remarks = livestock.getRemarks();
        this.marketTypeName = livestock.getMarketTypeName();
        this.autonomousName = livestock.getAutonomousName();
        this.checkDate = livestock.getCheckDate();
    }
}
