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
import sn.ept.git.dic2.ventevelos.entities.Marque;
import sn.ept.git.dic2.ventevelos.facades.MarqueFacade;

import java.util.List;

@Path("/v1/marques")
public class MarqueResource {

    @EJB
    private MarqueFacade marqueFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Add a mark",
            description = "Add the given mark",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The mark with the specified id already exists"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "The mark is successfully added",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Mark added",
                                                            value = "{msg: The mark number 1 was added successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response addMarque(Marque marq) {
        Marque tmp_marque = marqueFacade.find(marq.getId());

        if (tmp_marque != null) {
            MyResponse myResponse = new MyResponse("The marque " + tmp_marque.getNom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        marqueFacade.create(marq);
        MyResponse myResponse = new MyResponse("The marque " + marq.getNom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get all marks",
            description = "Get all marks",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Marks found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Marks found",
                                                            value = "[{\n" +
                                                                    "  \"id\": 1,\n" +
                                                                    "  \"nom\": \"Haro\"\n" +
                                                                    "}]"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public List<Marque> getMarqueList() {
        return marqueFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get a mark",
            description = "Get the mark whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The mark with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The mark with the specified id is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Mark found",
                                                            value = "{\n" +
                                                                    "  \"id\": 1,\n" +
                                                                    "  \"nom\": \"Haro\"\n" +
                                                                    "}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response find(@PathParam("number") String number) {
        Marque marque = marqueFacade.find(Long.parseLong(number));
        if (marque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(marque).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Edit a mark",
            description = "Edit the mark whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The mark with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The mark with the specified id is successfully edited",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Mark edited",
                                                            value = "{msg: The mark number 1 was edited successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response editMarque(Marque marq) {
        Marque tmp_marque = marqueFacade.find(marq.getId());

        if (tmp_marque == null) {
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
    @Operation(
            summary = "Delete a mark",
            description = "Delete the mark whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The mark with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The mark with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Mark deleted",
                                                            value = "{msg: The mark Haro was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("number") String number) {
        Marque marque = marqueFacade.find(Long.parseLong(number));
        if (marque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        marqueFacade.remove(marque);
        MyResponse myResponse = new MyResponse("The mark " + marque.getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
