package com.techstore.mapper;

import com.techstore.dto.ProductDTO;
import com.techstore.entity.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product){
        ProductDTO dto = new ProductDTO();
        dto.id = product.id;
        dto.name = product.name;
        dto.description = product.description;
        dto.price = product.price;
        dto.stock = product.stock;
        return dto;
    }
}
