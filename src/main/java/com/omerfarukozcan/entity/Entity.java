package com.omerfarukozcan.entity;

import lombok.Data;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static com.omerfarukozcan.entity.Status.ACTIVE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Data
@MappedSuperclass
public abstract class Entity implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Enumerated(STRING)
    private Status status = ACTIVE;

}
