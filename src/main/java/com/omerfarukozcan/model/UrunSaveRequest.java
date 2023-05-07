package com.omerfarukozcan.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UrunSaveRequest {

    private Long urunId;
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private String description;
    private String image;

}
