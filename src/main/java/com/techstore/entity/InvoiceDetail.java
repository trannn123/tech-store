package com.techstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class InvoiceDetail extends PanacheEntity {

    @ManyToOne
    @NotNull
    @JsonIgnore
    public Invoice invoice;

    @ManyToOne
    @NotNull
    public Product product;

    @NotNull
    @Positive
    public Integer quantity;

    @NotNull
    public Double price;}
