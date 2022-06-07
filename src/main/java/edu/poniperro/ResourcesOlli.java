package edu.poniperro;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}