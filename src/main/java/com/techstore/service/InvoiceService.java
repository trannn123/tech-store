package com.techstore.service;

import com.techstore.dto.CreateInvoiceRequest;
import com.techstore.dto.InvoiceItemRequest;
import com.techstore.entity.Invoice;
import com.techstore.entity.InvoiceDetail;
import com.techstore.entity.Product;
import com.techstore.entity.User;
import com.techstore.repository.InvoiceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class InvoiceService {

    @Inject
    InvoiceRepository repository;

    public List<Invoice> getAll(){
        return repository.listAll();
    }

    public Invoice getById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Invoice create(CreateInvoiceRequest request){
        User user = User.findById(request.userId);

        if(user==null){
            throw new RuntimeException("User not found");
        }

        Invoice invoice = new Invoice();
        invoice.user = user;
        invoice.status = "PENDING";
        invoice.totalAmount = 0.0;
        repository.persist(invoice);

        for(InvoiceItemRequest item :  request.items){
            Product product = Product.findById(item.productId);

            if(product==null){
                throw new RuntimeException("Product not found");
            }

            if(product.stock < item.quantity){
                throw new RuntimeException("Not enough stock");
            }

            product.stock = product.stock - item.quantity;

            InvoiceDetail detail = new InvoiceDetail();

            detail.invoice = invoice;
            detail.product = product;
            detail.quantity = item.quantity;
            detail.price = product.price;

            invoice.totalAmount += product.price * item.quantity;

            detail.persist();
        }
        return invoice;
    }

    @Transactional
    public Invoice update(Long id, CreateInvoiceRequest request){
        Invoice invoice = repository.findById(id);

        if(invoice==null){
            throw new RuntimeException("Invoice not found");
        }

        for (InvoiceDetail detail : invoice.details){
            detail.product.stock += detail.quantity;
        }
        invoice.details.clear();

        invoice.totalAmount = 0.0;

        for(InvoiceItemRequest item : request.items){
            Product product = Product.findById(item.productId);

            if(product==null){
                throw new RuntimeException("Product not found");
            }

            if(product.stock < item.quantity){
                throw new RuntimeException("Not enough stock");
            }

            product.stock = product.stock - item.quantity;

            InvoiceDetail detail = new InvoiceDetail();

            detail.invoice = invoice;
            detail.product = product;
            detail.quantity = item.quantity;
            detail.price = product.price;

            invoice.totalAmount += product.price * item.quantity;

            detail.persist();
        }
        return invoice;
    }

    @Transactional
    public void delete(Long id){
        Invoice invoice = repository.findById(id);
        if(invoice == null){
            throw new RuntimeException("Invoice not found");
        }
        for(InvoiceDetail detail : invoice.details){
            detail.product.stock += detail.quantity;
        }
        repository.delete(invoice);
    }
}
