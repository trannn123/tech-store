package com.techstore.resource;

import com.techstore.entity.Product;
import com.techstore.repository.ProductRepository;
import com.techstore.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    @PermitAll
    public List<Product> getAll(){
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getById(@PathParam("id") Long id){
        Product product = service.getById(id);
        if(product==null){
            return Response.status(404).build();
        }
        return Response.ok(product).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    public Response create(@Valid Product product){
        service.create(product);
        return Response.status(201).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response update(@PathParam("id") Long id, Product request){
        Product updated = service.update(id, request);
        if(updated==null){
            return Response.status(404).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id){
        boolean deleted = service.delete(id);
        if(!deleted){
            return Response.status(404).build();
        }
        return Response.noContent().build();
    }


}
