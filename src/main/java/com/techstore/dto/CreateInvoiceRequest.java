package com.techstore.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateInvoiceRequest {

    @NotNull
    public Long userId;

    @NotNull
    public List<InvoiceItemRequest> items;
}
