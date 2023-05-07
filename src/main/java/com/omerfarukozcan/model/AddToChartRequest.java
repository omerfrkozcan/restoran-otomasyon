package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class AddToChartRequest {

    private Long urunId;
    private String sessionId;

}
