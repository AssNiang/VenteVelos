package sn.ept.git.dic2.ventevelos.api.webServiceResources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventevelos.api.MyResponse;
import sn.ept.git.dic2.ventevelos.entities.Produit;
import sn.ept.git.dic2.ventevelos.facades.ProduitFacade;

import java.util.List;

@Path("/produits")
public class ProduitResource {

    @EJB
    private ProduitFacade produitFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProduit(Produit prod){
        Produit tmp_produit = produitFacade.find(prod.getId());

        if(tmp_produit != null){
            MyResponse myResponse = new MyResponse("The produit " + tmp_produit.getNom() + " already exists.");
            return Response.status(Response.Status.OK).entity(myResponse).build();
        }
        produitFacade.create(prod);
        MyResponse myResponse = new MyResponse("The produit " + prod.getNom() + " was created successfully.");
        return Response.status(Response.Status.CREATED).entity(myResponse).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Produit> getProduitList () {
        return produitFacade.findAll();
    }


    @GET
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("number") String number) {
        Produit produit = produitFacade.find(Long.parseLong(number));
        if(produit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(produit).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response editProduit(Produit prod){
        Produit tmp_produit = produitFacade.find(prod.getId());

        if(tmp_produit == null){
            MyResponse myResponse = new MyResponse("The produit " + prod.getNom() + " doesn't exist.");
            return Response.status(Response.Status.NOT_FOUND).entity(myResponse).build();
        }
        produitFacade.edit(prod);
        MyResponse myResponse = new MyResponse("The produit " + tmp_produit.getNom() + " was edited successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }

    @DELETE
    @Path("{number}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response delete(@PathParam("number") String number) {
        Produit produit = produitFacade.find(Long.parseLong(number));
        if(produit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        produitFacade.remove(produit);
        MyResponse myResponse = new MyResponse("The produit " + produit.getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}