package com.omerfarukozcan.model;

import lombok.Data;

import static java.lang.Boolean.FALSE;

@Data
public class AddToChartResponse {

    private boolean success;

    public AddToChartResponse() {
    }

    public AddToChartResponse(boolean success) {
        this.success = success;
    }

}
