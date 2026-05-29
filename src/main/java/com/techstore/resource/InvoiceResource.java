package com.techstore.resource;

import com.techstore.dto.CreateInvoiceRequest;
import com.techstore.entity.Invoice;
import com.techstore.service.InvoiceService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/invoices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    @Inject
    InvoiceService service;

    @GET
    public List<Invoice> getAll(){
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        Invoice invoice = service.getById(id);
        if(invoice==null){
            return Response.status(404).build();
        }
        return Response.ok(invoice).build();
    }

    @POST
    public Response create(@Valid CreateInvoiceRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid CreateInvoiceRequest request ){
        Invoice invoice = service.update(id, request);
        return Response.ok(invoice).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.ok("Invoice deleted").build();
    }
}
