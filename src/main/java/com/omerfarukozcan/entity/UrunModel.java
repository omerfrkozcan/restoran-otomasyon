package com.omerfarukozcan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class UrunModel extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;
    private String image;

}
