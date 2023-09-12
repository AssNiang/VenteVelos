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
import sn.ept.git.dic2.ventevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventevelos.facades.ArticleCommandeFacade;

import java.util.List;

@Path("/v1/articleCommandes")
public class ArticleCommandeResource {

    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Add an ordered item",
            description = "Add the given ordered item",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The ordered item with the specified id already exists"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "The ordered item is successfully added",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Ordered item added",
                                                            value = "{msg: The ordered item number 1 of the order number 1 was added successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response addArticleCommande(ArticleCommande articleComm){
        ArticleCommande tmp_articleCommande = articleCommandeFacade.find(articleComm.getLigne(), articleComm.getCommande().getNumero());

        if(tmp_articleCommande != null){
            MyResponse myResponse = new MyResponse("The articleCommande " + tmp_articleCommande.getLigne() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        articleCommandeFacade.create(articleComm);
        MyResponse myResponse = new MyResponse("The articleCommande " + articleComm.getLigne() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get all ordered items",
            description = "Get all ordered items",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ordered items found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Ordered items found",
                                                            value = "[{\n" +
                                                                    "  \"commande\": {\n" +
                                                                    "    \"client\": {\n" +
                                                                    "      \"id\": 25,\n" +
                                                                    "      \"nom\": \"DIOP\",\n" +
                                                                    "      \"prenom\": \"Ousmane\",\n" +
                                                                    "      \"adresse\": \"PA-U26\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"ousmane@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"telephone\": \"771234567\",\n" +
                                                                    "      \"ville\": \"Dakar\",\n" +
                                                                    "      \"actif\": 25,\n" +
                                                                    "      \"magasin\": {\n" +
                                                                    "        \"adresse\": \"PA-U25\",\n" +
                                                                    "        \"code_zip\": \"SN\",\n" +
                                                                    "        \"email\": \"magasin@ept.sn\",\n" +
                                                                    "        \"etat\": \"Senegal\",\n" +
                                                                    "        \"id\": 22,\n" +
                                                                    "        \"nom\": \"nom-magasin\",\n" +
                                                                    "        \"telephone\": \"771112233\",\n" +
                                                                    "        \"ville\": \"Dakar\"\n" +
                                                                    "      }\n" +
                                                                    "    },\n" +
                                                                    "    \"date_commande\": \"2023-09-13T00:00:00Z[UTC]\",\n" +
                                                                    "    \"date_livraison\": \"2023-09-30T12:00:00Z[UTC]\",\n" +
                                                                    "    \"employe\": {\n" +
                                                                    "      \"id\": 25,\n" +
                                                                    "      \"nom\": \"DIOP\",\n" +
                                                                    "      \"prenom\": \"Ousmane\",\n" +
                                                                    "      \"adresse\": \"PA-U26\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"ousmane@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"telephone\": \"771234567\",\n" +
                                                                    "      \"ville\": \"Dakar\",\n" +
                                                                    "      \"actif\": 25,\n" +
                                                                    "      \"magasin\": {\n" +
                                                                    "        \"adresse\": \"PA-U25\",\n" +
                                                                    "        \"code_zip\": \"SN\",\n" +
                                                                    "        \"email\": \"magasin@ept.sn\",\n" +
                                                                    "        \"etat\": \"Senegal\",\n" +
                                                                    "        \"id\": 22,\n" +
                                                                    "        \"nom\": \"nom-magasin\",\n" +
                                                                    "        \"telephone\": \"771112233\",\n" +
                                                                    "        \"ville\": \"Dakar\"\n" +
                                                                    "      }\n" +
                                                                    "    },\n" +
                                                                    "    \"magasin\": {\n" +
                                                                    "      \"adresse\": \"PA-U25\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"magasin@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"id\": 22,\n" +
                                                                    "      \"nom\": \"nom-magasin\",\n" +
                                                                    "      \"telephone\": \"771112233\",\n" +
                                                                    "      \"ville\": \"Dakar\"\n" +
                                                                    "    },\n" +
                                                                    "    \"numero\": 1,\n" +
                                                                    "    \"statut\": 1\n" +
                                                                    "  },\n" +
                                                                    "  \"ligne\": 1,\n" +
                                                                    "  \"prix_depart\": 10.0,\n" +
                                                                    "  \"produit\": {\n" +
                                                                    "    \"annee_model\": 2023,\n" +
                                                                    "    \"categorie\": {\n" +
                                                                    "      \"id\": 1,\n" +
                                                                    "      \"nom\": \"Children Bicycles\"\n" +
                                                                    "    },\n" +
                                                                    "    \"id\": 1,\n" +
                                                                    "    \"marque\": {\n" +
                                                                    "      \"id\": 1,\n" +
                                                                    "      \"nom\": \"Haro\"\n" +
                                                                    "    },\n" +
                                                                    "    \"nom\": \"nom produit\",\n" +
                                                                    "    \"prix_depart\": 10.0\n" +
                                                                    "  },\n" +
                                                                    "  \"quantite\": 10,\n" +
                                                                    "  \"remise\": 2.0\n" +
                                                                    "}]"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public List<ArticleCommande> getArticleCommandeList () {
        return articleCommandeFacade.findAll();
    }


    @GET
    @Path("{ligne}_{numComm}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get an ordered item",
            description = "Get the ordered item whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The ordered item with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The ordered item with the specified id is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Ordered item found",
                                                            value = "{\n" +
                                                                    "  \"commande\": {\n" +
                                                                    "    \"client\": {\n" +
                                                                    "      \"id\": 25,\n" +
                                                                    "      \"nom\": \"DIOP\",\n" +
                                                                    "      \"prenom\": \"Ousmane\",\n" +
                                                                    "      \"adresse\": \"PA-U26\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"ousmane@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"telephone\": \"771234567\",\n" +
                                                                    "      \"ville\": \"Dakar\",\n" +
                                                                    "      \"actif\": 25,\n" +
                                                                    "      \"magasin\": {\n" +
                                                                    "        \"adresse\": \"PA-U25\",\n" +
                                                                    "        \"code_zip\": \"SN\",\n" +
                                                                    "        \"email\": \"magasin@ept.sn\",\n" +
                                                                    "        \"etat\": \"Senegal\",\n" +
                                                                    "        \"id\": 22,\n" +
                                                                    "        \"nom\": \"nom-magasin\",\n" +
                                                                    "        \"telephone\": \"771112233\",\n" +
                                                                    "        \"ville\": \"Dakar\"\n" +
                                                                    "      }\n" +
                                                                    "    },\n" +
                                                                    "    \"date_commande\": \"2023-09-13T00:00:00Z[UTC]\",\n" +
                                                                    "    \"date_livraison\": \"2023-09-30T12:00:00Z[UTC]\",\n" +
                                                                    "    \"employe\": {\n" +
                                                                    "      \"id\": 25,\n" +
                                                                    "      \"nom\": \"DIOP\",\n" +
                                                                    "      \"prenom\": \"Ousmane\",\n" +
                                                                    "      \"adresse\": \"PA-U26\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"ousmane@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"telephone\": \"771234567\",\n" +
                                                                    "      \"ville\": \"Dakar\",\n" +
                                                                    "      \"actif\": 25,\n" +
                                                                    "      \"magasin\": {\n" +
                                                                    "        \"adresse\": \"PA-U25\",\n" +
                                                                    "        \"code_zip\": \"SN\",\n" +
                                                                    "        \"email\": \"magasin@ept.sn\",\n" +
                                                                    "        \"etat\": \"Senegal\",\n" +
                                                                    "        \"id\": 22,\n" +
                                                                    "        \"nom\": \"nom-magasin\",\n" +
                                                                    "        \"telephone\": \"771112233\",\n" +
                                                                    "        \"ville\": \"Dakar\"\n" +
                                                                    "      }\n" +
                                                                    "    },\n" +
                                                                    "    \"magasin\": {\n" +
                                                                    "      \"adresse\": \"PA-U25\",\n" +
                                                                    "      \"code_zip\": \"SN\",\n" +
                                                                    "      \"email\": \"magasin@ept.sn\",\n" +
                                                                    "      \"etat\": \"Senegal\",\n" +
                                                                    "      \"id\": 22,\n" +
                                                                    "      \"nom\": \"nom-magasin\",\n" +
                                                                    "      \"telephone\": \"771112233\",\n" +
                                                                    "      \"ville\": \"Dakar\"\n" +
                                                                    "    },\n" +
                                                                    "    \"numero\": 1,\n" +
                                                                    "    \"statut\": 1\n" +
                                                                    "  },\n" +
                                                                    "  \"ligne\": 1,\n" +
                                                                    "  \"prix_depart\": 10.0,\n" +
                                                                    "  \"produit\": {\n" +
                                                                    "    \"annee_model\": 2023,\n" +
                                                                    "    \"categorie\": {\n" +
                                                                    "      \"id\": 1,\n" +
                                                                    "      \"nom\": \"Children Bicycles\"\n" +
                                                                    "    },\n" +
                                                                    "    \"id\": 1,\n" +
                                                                    "    \"marque\": {\n" +
                                                                    "      \"id\": 1,\n" +
                                                                    "      \"nom\": \"Haro\"\n" +
                                                                    "    },\n" +
                                                                    "    \"nom\": \"nom produit\",\n" +
                                                                    "    \"prix_depart\": 10.0\n" +
                                                                    "  },\n" +
                                                                    "  \"quantite\": 10,\n" +
                                                                    "  \"remise\": 2.0\n" +
                                                                    "}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response find(@PathParam("ligne") String ligne, @PathParam("numComm") String numComm) {
        ArticleCommande articleCommande = articleCommandeFacade.find(Long.parseLong(ligne), Long.parseLong(numComm));
        if(articleCommande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(articleCommande).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Edit an ordered item",
            description = "Edit the ordered item whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The ordered item with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The ordered item with the specified id is successfully edited",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Ordered item edited",
                                                            value = "{msg: The ordered item number 1 was edited successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response editArticleCommande(ArticleCommande articleComm){
        ArticleCommande tmp_articleCommande = articleCommandeFacade.find(articleComm.getLigne(), articleComm.getCommande().getNumero());

        if(tmp_articleCommande == null){
            MyResponse myResponse = new MyResponse("The articleCommande " + articleComm.getLigne() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        articleCommandeFacade.edit(articleComm);
        MyResponse myResponse = new MyResponse("The articleCommande " + tmp_articleCommande.getLigne() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{ligne}_{numComm}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Delete an ordered item",
            description = "Delete the ordered item whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The ordered item with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The ordered item with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Ordered item deleted",
                                                            value = "{msg: The ordered item number 1 of the order number 1 was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("ligne") String ligne, @PathParam("numComm") String numComm) {
        ArticleCommande articleCommande = articleCommandeFacade.find(Long.parseLong(ligne), Long.parseLong(numComm));
        if(articleCommande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        articleCommandeFacade.remove(articleCommande);
        MyResponse myResponse = new MyResponse("The ordered item " + articleCommande.getLigne() + " of the order number " + numComm + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
