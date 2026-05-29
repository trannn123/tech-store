package com.techstore.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Invoice extends PanacheEntity {

    @ManyToOne
    public User user;

    public LocalDateTime createdAt;

    public Double totalAmount;

    public String status;

    @OneToMany(mappedBy = "invoice",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    public List<InvoiceDetail> details;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }
}
