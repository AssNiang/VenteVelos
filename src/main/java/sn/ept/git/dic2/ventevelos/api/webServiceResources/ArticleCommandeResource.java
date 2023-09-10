package sn.ept.git.dic2.ventevelos.api.webServiceResources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventevelos.api.MyResponse;
import sn.ept.git.dic2.ventevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventevelos.facades.ArticleCommandeFacade;

import java.util.List;

@Path("/articleCommandes")
public class ArticleCommandeResource {

    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public List<ArticleCommande> getArticleCommandeList () {
        return articleCommandeFacade.findAll();
    }


    @GET
    @Path("{ligne}_{numComm}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("ligne") String ligne, @PathParam("numComm") String numComm) {
        ArticleCommande articleCommande = articleCommandeFacade.find(Long.parseLong(ligne), Long.parseLong(numComm));
        if(articleCommande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FOUND).entity(articleCommande).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
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
    public Response delete(@PathParam("ligne") String ligne, @PathParam("numComm") String numComm) {
        ArticleCommande articleCommande = articleCommandeFacade.find(Long.parseLong(ligne), Long.parseLong(numComm));
        if(articleCommande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        articleCommandeFacade.remove(articleCommande);
        MyResponse myResponse = new MyResponse("The articleCommande " + articleCommande.getLigne() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(myResponse).build();
    }
}
