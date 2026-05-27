package com.techstore.resource;

import com.techstore.entity.Category;
import com.techstore.service.CategoryService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService service;

    @GET
    public List<Category> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Category category = service.getById(id);
        if (category == null) {
            return Response.status(404).build();
        }
        return Response.ok(category).build();
    }

    @POST
    public Response create(@Valid Category category) {
        service.create(category);
        return Response.status(201).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id,
                           Category request) {
        Category updated = service.update(id, request);
        if (updated == null) {
            return Response.status(404).build();
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    }
}