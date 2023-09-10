package sn.ept.git.dic2.ventevelos.api.webServiceResources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventevelos.api.MyResponse;
import sn.ept.git.dic2.ventevelos.entities.Commande;
import sn.ept.git.dic2.ventevelos.facades.CommandeFacade;

import java.util.List;

@Path("/v1/commandes")
public class CommandeResource {

    @EJB
    private CommandeFacade commandeFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCommande(Commande comm){
        Commande tmp_commande = commandeFacade.find(comm.getNumero());

        if(tmp_commande != null){
            MyResponse myResponse = new MyResponse("The commande " + tmp_commande.getNumero() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        commandeFacade.create(comm);
        MyResponse myResponse = new MyResponse("The commande " + comm.getNumero() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Commande> getCommandeList () {
        return commandeFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("number") String number) {
        Commande commande = commandeFacade.find(Long.parseLong(number));
        if(commande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(commande).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editCommande(Commande comm){
        Commande tmp_commande = commandeFacade.find(comm.getNumero());

        if(tmp_commande == null){
            MyResponse myResponse = new MyResponse("The commande " + comm.getNumero() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        commandeFacade.edit(comm);
        MyResponse myResponse = new MyResponse("The commande " + tmp_commande.getNumero() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Delete an order",
            description = "Delete the order whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The order with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The order with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Order deleted",
                                                            value = "{msg: The order 1 was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("number") String number) {
        Commande commande = commandeFacade.find(Long.parseLong(number));
        if(commande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        commandeFacade.remove(commande);
        MyResponse myResponse = new MyResponse("The commande " + commande.getNumero() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
