package com.omerfarukozcan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

}
