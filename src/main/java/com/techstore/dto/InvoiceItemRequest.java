package com.techstore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InvoiceItemRequest {

    @NotNull
    public Long productId;

    @NotNull
    @Positive
    public Integer quantity;
}
