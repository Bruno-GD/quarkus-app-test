package edu.poniperro;

import edu.poniperro.dominio.Usuaria;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class ResourcesOlli {

    @Inject
    ServiceOlli service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/wellcome")
    public String hello() {
        return "Wellcome Ollivanders!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/usuaria/{nombre}")
    public Response getUsuaria(@Valid String nombre) {
        Usuaria usuaria = service.cargaUsuaria(nombre);

        // si la Usuaria no existe error 404 NOT FOUND
        if (usuaria.getNombre().isEmpty())
            return Response.status(404).build();

        return Response.ok(usuaria).build();
    }
}