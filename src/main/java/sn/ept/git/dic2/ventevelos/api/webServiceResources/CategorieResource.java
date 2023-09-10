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
import sn.ept.git.dic2.ventevelos.entities.Categorie;
import sn.ept.git.dic2.ventevelos.facades.CategorieFacade;

import java.util.List;

@Path("/v1/categories")
public class CategorieResource {

    @EJB
    private CategorieFacade categorieFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addCategorie(Categorie cat){
        Categorie tmp_categorie = categorieFacade.find(cat.getId());

        if(tmp_categorie != null){
            MyResponse myResponse = new MyResponse("The categorie " + tmp_categorie.getNom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        categorieFacade.create(cat);
        MyResponse myResponse = new MyResponse("The categorie " + cat.getNom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Categorie> getCategorieList () {
        return categorieFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("number") String number) {
        Categorie categorie = categorieFacade.find(Long.parseLong(number));
        if(categorie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(categorie).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editCategorie(Categorie cat){
        Categorie tmp_categorie = categorieFacade.find(cat.getId());

        if(tmp_categorie == null){
            MyResponse myResponse = new MyResponse("The categorie " + cat.getNom() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        categorieFacade.edit(cat);
        MyResponse myResponse = new MyResponse("The categorie " + tmp_categorie.getNom() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Delete a category",
            description = "Delete the category whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The category with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The category with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Category deleted",
                                                            value = "{msg: The category Children Bicycle was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("number") String number) {
        Categorie categorie = categorieFacade.find(Long.parseLong(number));
        if(categorie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categorieFacade.remove(categorie);
        MyResponse myResponse = new MyResponse("The categorie " + categorie.getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
