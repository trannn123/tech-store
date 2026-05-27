package com.techstore.service;

import com.techstore.entity.Product;
import com.techstore.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    public List<Product> getAll(){
        return repository.listAll();
    }

    public Product getById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public void create(Product product){
        repository.persist(product);
    }

    @Transactional
    public Product update(Long id, Product request){
        Product product = repository.findById(id);
        if(product==null){
            return null;
        }
        else {
            product.name = request.name;
            product.description = request.description;
            product.price = request.price;
            product.stock = request.stock;

            return product;
        }
    }

    @Transactional
    public boolean delete(Long id){
        Product product = repository.findById(id);
        if(product==null){
            return false;
        }
        else{
            repository.delete(product);
            return true;
        }
    }


}
