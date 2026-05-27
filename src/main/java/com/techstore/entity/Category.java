package com.techstore.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category extends PanacheEntity {

    @NotBlank(message = "Category name is required")
    public String name;
}