package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class CheckoutResponse {

    private boolean success;
    private String orderCode;

    public CheckoutResponse() {
    }

    public CheckoutResponse(String orderCode) {
        setOrderCode(orderCode);
        setSuccess(true);
    }

}
