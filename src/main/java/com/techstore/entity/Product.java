package com.techstore.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

@Entity
public class Product extends PanacheEntity {

    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Description is required")
    public String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    public Double price;

    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock must be >= 0")
    public Integer stock;

    @ManyToOne
    @NotNull(message = "Category is required")
    public Category category;
}