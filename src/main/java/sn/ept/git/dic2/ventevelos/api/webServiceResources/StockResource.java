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
import sn.ept.git.dic2.ventevelos.entities.Stock;
import sn.ept.git.dic2.ventevelos.facades.StockFacade;

import java.util.List;

@Path("/v1/stocks")
public class StockResource {

    @EJB
    private StockFacade stockFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Add a stock",
            description = "Add the given stock",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The stock with the specified id already exists"
                    ),
                    @ApiResponse(
                            responseCode = "201",
                            description = "The stock is successfully added",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Stock added",
                                                            value = "{msg: The stock in the shop shop-A of the product product-A was added successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response addStock(Stock st){
        Stock tmp_stock = stockFacade.find(st.getMagasin().getId(), st.getProduit().getId());

        if(tmp_stock != null){
            MyResponse myResponse = new MyResponse("The stock " + tmp_stock.getMagasin().getNom() + '-' + tmp_stock.getProduit().getNom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        stockFacade.create(st);
        MyResponse myResponse = new MyResponse("The stock " + st.getMagasin().getNom() + '-' + st.getProduit().getNom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get all stocks",
            description = "Get all stocks",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Stocks found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Stocks found",
                                                            value = "[{\n" +
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
                                                                    "  \"quantite\": 10\n" +
                                                                    "}]"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public List<Stock> getStockList () {
        return stockFacade.findAll();
    }


    @GET
    @Path("{numMag}_{numProd}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Get a stock",
            description = "Get the stock whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The stock with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The stock with the specified id is found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Stock found",
                                                            value = "{\n" +
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
                                                                    "  \"quantite\": 10\n" +
                                                                    "}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response find(@PathParam("numMag") String numMag, @PathParam("numProd") String numProd) {
        Stock stock = stockFacade.find(Long.parseLong(numMag), Long.parseLong(numProd));
        if(stock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(stock).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Edit a stock",
            description = "Edit the stock whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The stock with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The stock with the specified id is successfully edited",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Stock edited",
                                                            value = "{msg: The stock in the shop shop-A of the product product-A was edited successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response editStock(Stock st){
        Stock tmp_stock = stockFacade.find(st.getMagasin().getId(), st.getProduit().getId());

        if(tmp_stock == null){
            MyResponse myResponse = new MyResponse("The stock " + st.getMagasin().getNom() + '-' + st.getProduit().getNom() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        stockFacade.edit(st);
        MyResponse myResponse = new MyResponse("The stock " + tmp_stock.getMagasin().getNom() + '-' + tmp_stock.getProduit().getNom() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{numMag}_{numProd}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Delete a stock",
            description = "Delete the stock whose id match the given one as parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "The stock with the specified id is not found"
                    ),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The stock with the specified id is successfully deleted",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "Stock deleted",
                                                            value = "{msg: The stock in the shop Shop-A of the product Product-A was deleted successfully.}"
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    public Response delete(@PathParam("numMag") String numMag, @PathParam("numProd") String numProd) {
        Stock stock = stockFacade.find(Long.parseLong(numMag), Long.parseLong(numProd));
        if(stock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stockFacade.remove(stock);
        MyResponse myResponse = new MyResponse("The stock in the shop " + stock.getMagasin().getNom() + " of the product " + stock.getProduit().getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
