package com.omerfarukozcan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class OrderModel extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private String sessionId;

}
