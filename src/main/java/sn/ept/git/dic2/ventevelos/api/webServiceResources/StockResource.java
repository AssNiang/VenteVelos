package sn.ept.git.dic2.ventevelos.api.webServiceResources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventevelos.api.MyResponse;
import sn.ept.git.dic2.ventevelos.entities.Stock;
import sn.ept.git.dic2.ventevelos.facades.StockFacade;

import java.util.List;

@Path("/stocks")
public class StockResource {

    @EJB
    private StockFacade stockFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public List<Stock> getStockList () {
        return stockFacade.findAll();
    }


    @GET
    @Path("{numMag}_{numProd}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("numMag") String numMag, @PathParam("numProd") String numProd) {
        Stock stock = stockFacade.find(Long.parseLong(numMag), Long.parseLong(numProd));
        if(stock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(stock).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public Response delete(@PathParam("numMag") String numMag, @PathParam("numProd") String numProd) {
        Stock stock = stockFacade.find(Long.parseLong(numMag), Long.parseLong(numProd));
        if(stock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stockFacade.remove(stock);
        MyResponse myResponse = new MyResponse("The stock " + stock.getMagasin().getNom() + '-' + stock.getProduit().getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
