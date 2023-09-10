package sn.ept.git.dic2.ventevelos.api.webServiceResources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventevelos.api.MyResponse;
import sn.ept.git.dic2.ventevelos.entities.Marque;
import sn.ept.git.dic2.ventevelos.facades.MarqueFacade;

import java.util.List;

@Path("/marques")
public class MarqueResource {

    @EJB
    private MarqueFacade marqueFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addMarque(Marque marq){
        Marque tmp_marque = marqueFacade.find(marq.getId());

        if(tmp_marque != null){
            MyResponse myResponse = new MyResponse("The marque " + tmp_marque.getNom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        marqueFacade.create(marq);
        MyResponse myResponse = new MyResponse("The marque " + marq.getNom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Marque> getMarqueList () {
        return marqueFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("number") String number) {
        Marque marque = marqueFacade.find(Long.parseLong(number));
        if(marque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(marque).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editMarque(Marque marq){
        Marque tmp_marque = marqueFacade.find(marq.getId());

        if(tmp_marque == null){
            MyResponse myResponse = new MyResponse("The marque " + marq.getNom() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        marqueFacade.edit(marq);
        MyResponse myResponse = new MyResponse("The marque " + tmp_marque.getNom() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("number") String number) {
        Marque marque = marqueFacade.find(Long.parseLong(number));
        if(marque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        marqueFacade.remove(marque);
        MyResponse myResponse = new MyResponse("The marque " + marque.getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}