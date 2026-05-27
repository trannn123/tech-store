package com.techstore.resource;

import com.techstore.dto.LoginRequest;
import com.techstore.dto.LoginResponse;
import com.techstore.dto.ProductDTO;
import com.techstore.entity.User;
import com.techstore.service.UserService;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UserService service;

    @POST
    @Path("/register")
    public Response register(User user){
        service.register(user);
        return Response.status(201).build();
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest request){
        User user = service.findByUsername(request.username);
        if(user==null){
            return Response.status(401).build();
        }
        if(!user.password.equals(request.password)){
            return Response.status(401).build();
        }
        String token = Jwt.issuer("tech-store").upn(user.username).groups(user.role).sign();
        return Response.ok(new LoginResponse(token)).build();
    }
}
