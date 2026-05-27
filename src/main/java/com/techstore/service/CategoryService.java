package com.techstore.service;

import com.techstore.entity.Category;
import com.techstore.repository.CategoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CategoryService {
    @Inject
    CategoryRepository repository;

    public List<Category> getAll(){
        return repository.listAll();
    }

    public Category getById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public void create(Category category){
        repository.persist(category);
    }

    @Transactional
    public Category update(Long id, Category request){
        Category category = repository.findById(id);
        if(category==null){
            return null;
        }
        else {
            category.name = request.name;
            return category;
        }
    }

    @Transactional
    public boolean delete(Long id){
        Category category = repository.findById(id);
        if(category==null){
            return false;
        }

        repository.delete(category);
        return true;
    }
}
