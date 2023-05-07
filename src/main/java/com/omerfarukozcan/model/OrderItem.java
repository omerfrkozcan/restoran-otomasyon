package com.omerfarukozcan.model;

import lombok.Data;

@Data
public class OrderItem {

    private Long orderId;
    private String orderCode;
    private String orderDate;
    private String name;
    private String email;
    private String note;
    private String status;
    private String statusDesc;

}
