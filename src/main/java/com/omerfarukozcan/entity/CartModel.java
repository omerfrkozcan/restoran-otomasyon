package com.omerfarukozcan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class CartModel extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    @Column(nullable = false)
    private Long urunId;

    @Column(nullable = false)
    private Integer count = 0;

    @Column(nullable = false)
    private String sessionId;

}
