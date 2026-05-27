package com.techstore.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    @NotBlank(message = "Name is required")
    public String username;

    @NotBlank(message = "Password is required")
    public String password;

    @NotBlank(message = "Role is required")
    public String role;
}
