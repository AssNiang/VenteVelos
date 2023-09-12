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
    @Operation(
            summary = "Add an order",
            description = "Add the given commande",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The order with the specified id already exists"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "The order is successfully added",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Order added",
                                                            value = "{msg: The order number 1 was added successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response addCommande(Commande comm){
        Commande tmp_commande = commandeFacade.find(comm.getNumero());

        if(tmp_commande != null){
            MyResponse myResponse = new MyResponse("The order " + tmp_commande.getNumero() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        commandeFacade.create(comm);
        MyResponse myResponse = new MyResponse("The order " + comm.getNumero() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get all orders",
            description = "Get all orders",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Commandes found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Commandes found",
                                                            value = "[{\n" +
                                                                    "  \"client\": {\n" +
                                                                    "    \"id\": 22,\n" +
                                                                    "    \"nom\": \"NIANG\",\n" +
                                                                    "    \"prenom\": \"Ass Malick\",\n" +
                                                                    "    \"adresse\": \"PA-U25\",\n" +
                                                                    "    \"code_zip\": \"SN\",\n" +
                                                                    "    \"email\": \"nianga@ept.sn\",\n" +
                                                                    "    \"etat\": \"Senegal\",\n" +
                                                                    "    \"telephone\": \"772914064\",\n" +
                                                                    "    \"ville\": \"Dakar\"\n" +
                                                                    "  },\n" +
                                                                    "  \"date_commande\": \"2023-09-13T00:00:00Z[UTC]\",\n" +
                                                                    "  \"date_livraison\": \"2023-09-30T12:00:00Z[UTC]\",\n" +
                                                                    "  \"employe\": {\n" +
                                                                    "    \"id\": 25,\n" +
                                                                    "    \"nom\": \"DIOP\",\n" +
                                                                    "    \"prenom\": \"Ousmane\",\n" +
                                                                    "    \"adresse\": \"PA-U26\",\n" +
                                                                    "    \"code_zip\": \"SN\",\n" +
                                                                    "    \"email\": \"ousmane@ept.sn\",\n" +
                                                                    "    \"etat\": \"Senegal\",\n" +
                                                                    "    \"telephone\": \"771234567\",\n" +
                                                                    "    \"ville\": \"Dakar\",\n" +
                                                                    "    \"actif\": 25,\n" +
                                                                    "    \"magasin\": {\n" +
                                                                    "      \"adresse\": \"PA-U25\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"magasin@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"id\": 22,\n" +
                                                                    "      \"nom\": \"nom-magasin\",\n" +
                                                                    "      \"telephone\": \"771112233\",\n" +
                                                                    "      \"ville\": \"Dakar\"\n" +
                                                                    "    }\n" +
                                                                    "  },\n" +
                                                                    "  \"magasin\": {\n" +
                                                                    "    \"adresse\": \"PA-U25\",\n" +
                                                                    "    \"code_zip\": \"SN\",\n" +
                                                                    "    \"email\": \"magasin@ept.sn\",\n" +
                                                                    "    \"etat\": \"Senegal\",\n" +
                                                                    "    \"id\": 22,\n" +
                                                                    "    \"nom\": \"nom-magasin\",\n" +
                                                                    "    \"telephone\": \"771112233\",\n" +
                                                                    "    \"ville\": \"Dakar\"\n" +
                                                                    "  },\n" +
                                                                    "  \"numero\": 1,\n" +
                                                                    "  \"statut\": 1\n" +
                                                                    "}]"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public List<Commande> getCommandeList () {
        return commandeFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get an order",
            description = "Get the order whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The order with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The order with the specified id is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Commande found",
                                                            value = "{\n" +
                                                                    "  \"client\": {\n" +
                                                                    "    \"id\": 22,\n" +
                                                                    "    \"nom\": \"NIANG\",\n" +
                                                                    "    \"prenom\": \"Ass Malick\",\n" +
                                                                    "    \"adresse\": \"PA-U25\",\n" +
                                                                    "    \"code_zip\": \"SN\",\n" +
                                                                    "    \"email\": \"nianga@ept.sn\",\n" +
                                                                    "    \"etat\": \"Senegal\",\n" +
                                                                    "    \"telephone\": \"772914064\",\n" +
                                                                    "    \"ville\": \"Dakar\"\n" +
                                                                    "  },\n" +
                                                                    "  \"date_commande\": \"2023-09-13T00:00:00Z[UTC]\",\n" +
                                                                    "  \"date_livraison\": \"2023-09-30T12:00:00Z[UTC]\",\n" +
                                                                    "  \"employe\": {\n" +
                                                                    "    \"id\": 25,\n" +
                                                                    "    \"nom\": \"DIOP\",\n" +
                                                                    "    \"prenom\": \"Ousmane\",\n" +
                                                                    "    \"adresse\": \"PA-U26\",\n" +
                                                                    "    \"code_zip\": \"SN\",\n" +
                                                                    "    \"email\": \"ousmane@ept.sn\",\n" +
                                                                    "    \"etat\": \"Senegal\",\n" +
                                                                    "    \"telephone\": \"771234567\",\n" +
                                                                    "    \"ville\": \"Dakar\",\n" +
                                                                    "    \"actif\": 25,\n" +
                                                                    "    \"magasin\": {\n" +
                                                                    "      \"adresse\": \"PA-U25\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"magasin@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"id\": 22,\n" +
                                                                    "      \"nom\": \"nom-magasin\",\n" +
                                                                    "      \"telephone\": \"771112233\",\n" +
                                                                    "      \"ville\": \"Dakar\"\n" +
                                                                    "    }\n" +
                                                                    "  },\n" +
                                                                    "  \"magasin\": {\n" +
                                                                    "    \"adresse\": \"PA-U25\",\n" +
                                                                    "    \"code_zip\": \"SN\",\n" +
                                                                    "    \"email\": \"magasin@ept.sn\",\n" +
                                                                    "    \"etat\": \"Senegal\",\n" +
                                                                    "    \"id\": 22,\n" +
                                                                    "    \"nom\": \"nom-magasin\",\n" +
                                                                    "    \"telephone\": \"771112233\",\n" +
                                                                    "    \"ville\": \"Dakar\"\n" +
                                                                    "  },\n" +
                                                                    "  \"numero\": 1,\n" +
                                                                    "  \"statut\": 1\n" +
                                                                    "}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response find(@PathParam("number") String number) {
        Commande commande = commandeFacade.find(Long.parseLong(number));
        if(commande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(commande).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Edit an order",
            description = "Edit the order whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The order with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The order with the specified id is successfully edited",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Order edited",
                                                            value = "{msg: The order number 1 was edited successfully.}"
                                                    )
                                            }
                                            
                                    )
                            }
                    )
            }
    )
    public Response editCommande(Commande comm){
        Commande tmp_commande = commandeFacade.find(comm.getNumero());

        if(tmp_commande == null){
            MyResponse myResponse = new MyResponse("The order " + comm.getNumero() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        commandeFacade.edit(comm);
        MyResponse myResponse = new MyResponse("The order " + tmp_commande.getNumero() + " was edited successfully.");
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
        MyResponse myResponse = new MyResponse("The order " + commande.getNumero() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
