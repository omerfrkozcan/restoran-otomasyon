package com.omerfarukozcan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.omerfarukozcan.util.CommonUtil.izEmpty;
import static java.util.stream.Collectors.toList;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper = true)
public class UserModel extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleModel> roles;

    public String lang;

    public boolean isEnabled() {
        return this.enabled;
    }

    public List<String> getRolesString() {
        return izEmpty(roles)
                ? new ArrayList<>()
                : roles.stream()
                .map(RoleModel::getDescription)
                .distinct()
                .collect(toList());
    }

}






