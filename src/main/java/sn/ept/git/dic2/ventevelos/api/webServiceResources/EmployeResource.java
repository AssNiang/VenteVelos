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
import sn.ept.git.dic2.ventevelos.entities.Employe;
import sn.ept.git.dic2.ventevelos.facades.EmployeFacade;

import java.util.List;

@Path("/v1/employes")
public class EmployeResource {

    @EJB
    private EmployeFacade employeFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Add an employee",
            description = "Add the given employe",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The employee with the specified id already exists"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "The employee is successfully added",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Employe added",
                                                            value = "{msg: The employee number 1 was added successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response addEmploye(Employe c){
        Employe tmp_employe = employeFacade.find(c.getId());

        if(tmp_employe != null){
            MyResponse myResponse = new MyResponse("The employee " + tmp_employe.getPrenom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        employeFacade.create(c);
        MyResponse myResponse = new MyResponse("The employee " + c.getPrenom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get all employes",
            description = "Get all employes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Employes found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Employes found",
                                                            value = "[{\"id\":1,\"nom\":\"Jackson\",\"prenom\":\"Fabiola\",\"email\":\"fabiola.jackson@bikes.shop\",\"telephone\":\"(831) 555-5554\",\"actif\":1,\"magasin\":{\"adresse\":\"3700 Portola Drive\",\"code_zip\":\"95060\",\"email\":\"santacruz@bikes.shop\",\"etat\":\"CA\",\"id\":1,\"nom\":\"Santa Cruz Bikes\",\"telephone\":\"(831) 476-4321\",\"ville\":\"Santa Cruz\"}}]"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public List<Employe> getEmployeList () {
        return employeFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get an employee",
            description = "Get the employee whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The employee with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The employee with the specified id is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Employe found",
                                                            value = "{\"id\":1,\"nom\":\"Jackson\",\"prenom\":\"Fabiola\",\"email\":\"fabiola.jackson@bikes.shop\",\"telephone\":\"(831) 555-5554\",\"actif\":1,\"magasin\":{\"adresse\":\"3700 Portola Drive\",\"code_zip\":\"95060\",\"email\":\"santacruz@bikes.shop\",\"etat\":\"CA\",\"id\":1,\"nom\":\"Santa Cruz Bikes\",\"telephone\":\"(831) 476-4321\",\"ville\":\"Santa Cruz\"}}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response find(@PathParam("number") String number) {
        Employe employe = employeFacade.find(Long.parseLong(number));
        if(employe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(employe).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Edit an employee",
            description = "Edit the employee whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The employee with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The employee with the specified id is successfully edited",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Employe edited",
                                                            value = "{msg: The employee number 1 was edited successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response editEmploye(Employe c){
        Employe tmp_employe = employeFacade.find(c.getId());

        if(tmp_employe == null){
            MyResponse myResponse = new MyResponse("The employee " + c.getPrenom() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        employeFacade.edit(c);
        MyResponse myResponse = new MyResponse("The employee " + tmp_employe.getPrenom() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Delete an employee",
            description = "Delete the employee whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The employee with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The employee with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Employe deleted",
                                                            value = "{msg: The employee Moustapha was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("number") String number) {
        Employe employe = employeFacade.find(Long.parseLong(number));
        if(employe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        employeFacade.remove(employe);
        MyResponse myResponse = new MyResponse("The employee " + employe.getPrenom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
