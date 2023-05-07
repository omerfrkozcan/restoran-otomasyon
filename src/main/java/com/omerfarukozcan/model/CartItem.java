package com.omerfarukozcan.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {

    private Long id;
    private Long urunId;

    private String urunName;
    private String urunCategory;
    private BigDecimal urunPrice;
    private BigDecimal totalPrice;
    private String urunDescription;
    private String urunImage;

    private Integer count;
    private String sessionId;

}
