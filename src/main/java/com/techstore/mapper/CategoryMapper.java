package com.techstore.mapper;

import com.techstore.dto.CategoryDTO;
import com.techstore.dto.ProductDTO;
import com.techstore.entity.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category){
        CategoryDTO dto = new CategoryDTO();
        dto.id = category.id;
        dto.name = category.name;
        return dto;
    }
}
