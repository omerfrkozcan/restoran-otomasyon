package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class CheckoutRequest {

    private String name;
    private String email;
    private String note;
    private String sessionId;

}
