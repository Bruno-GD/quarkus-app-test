package edu.poniperro;

import edu.poniperro.dominio.Orden;
import edu.poniperro.dominio.Usuaria;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/ordena")
    public Response nuevaOrden(@Valid Orden orden) {
        Orden pedido = service.comanda(orden.getUser().getNombre(), orden.getItem().getNombre());
        if (pedido == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(orden).status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pedidos/{usuaria}")
    public Response getPedidos(@Valid Usuaria usuaria) {
        List<Orden> pedidos = service.cargaOrden(usuaria.getNombre());

        return Response.ok(pedidos).build();
    }
}