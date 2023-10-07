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
import sn.ept.git.dic2.ventevelos.entities.Client;
import sn.ept.git.dic2.ventevelos.facades.ClientFacade;

import java.util.List;

@Path("/v1/clients")
public class ClientResource {

    @EJB
    private ClientFacade clientFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Add a client",
            description = "Add the given client",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The client with the specified id already exists"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "The client is successfully added",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Client added",
                                                            value = "{msg: The client number 1 was added successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response addClient(Client c){
        Client tmp_client = clientFacade.find(c.getId());

        if(tmp_client != null){
            MyResponse myResponse = new MyResponse("The client " + tmp_client.getPrenom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        clientFacade.create(c);
        MyResponse myResponse = new MyResponse("The client " + c.getPrenom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get all clients",
            description = "Get all clients",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Clients found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Clients found",
                                                            value = "[{\n" +
                                                                    "  \"id\": 22,\n" +
                                                                    "  \"nom\": \"NIANG\",\n" +
                                                                    "  \"prenom\": \"Ass Malick\",\n" +
                                                                    "  \"adresse\": \"PA-U25\",\n" +
                                                                    "  \"code_zip\": \"SN\",\n" +
                                                                    "  \"email\": \"nianga@ept.sn\",\n" +
                                                                    "  \"etat\": \"Senegal\",\n" +
                                                                    "  \"telephone\": \"772914064\",\n" +
                                                                    "  \"ville\": \"Dakar\"\n" +
                                                                    "}]"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public List<Client> getClientList () {
        return clientFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get a client",
            description = "Get the client whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The client with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The client with the specified id is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Client found",
                                                            value = "{\n" +
                                                                    "  \"id\": 22,\n" +
                                                                    "  \"nom\": \"NIANG\",\n" +
                                                                    "  \"prenom\": \"Ass Malick\",\n" + "  \"adresse\": \"PA-U25\",\n" +
                                                                    "  \"code_zip\": \"SN\",\n" +
                                                                    "  \"email\": \"nianga@ept.sn\",\n" +
                                                                    "  \"etat\": \"Senegal\",\n" +
                                                                    "  \"telephone\": \"772914064\",\n" +
                                                                    "  \"ville\": \"Dakar\"\n" +
                                                                    "}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response find(@PathParam("number") String number) {
        Client client = clientFacade.find(Long.parseLong(number));
        if(client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(client).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Edit a client",
            description = "Edit the client whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The client with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The client with the specified id is successfully edited",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Client edited",
                                                            value = "{msg: The client number 1 was edited successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response editClient(Client c){
        Client tmp_client = clientFacade.find(c.getId());

        if(tmp_client == null){
            MyResponse myResponse = new MyResponse("The client " + c.getPrenom() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        clientFacade.edit(c);
        MyResponse myResponse = new MyResponse("The client " + tmp_client.getPrenom() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Delete a client",
            description = "Delete the client whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The client with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The client with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Client deleted",
                                                            value = "{msg: The client Moustapha was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("number") String number) {
        Client client = clientFacade.find(Long.parseLong(number));
        if(client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        clientFacade.remove(client);
        MyResponse myResponse = new MyResponse("The client " + client.getPrenom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
