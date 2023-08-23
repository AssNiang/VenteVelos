package sn.ept.git.dic2.ventevelos.mbeans.articleCommande;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Commande;
import sn.ept.git.dic2.ventevelos.entities.Produit;
import sn.ept.git.dic2.ventevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventevelos.facades.*;

import java.io.Serializable;
import java.util.List;

@Named("addArticleCommandeMBean")
@ViewScoped
public class AddArticleCommandeMBean implements Serializable {

    private String message;
    @EJB
    private ArticleCommandeFacade articleCommandeFacade;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private ProduitFacade produitFacade;

    private ArticleCommande articleCommande = new ArticleCommande();
    private Long selectedCommandeId;
    private Long selectedProduitId;

    public AddArticleCommandeMBean() {
        articleCommande.setLigne(1L);
        articleCommande.setQuantite(10);
        articleCommande.setPrix_depart(10.);
        articleCommande.setRemise(2.);
    }

    public String getMessage() {
        return message;
    }

    public ArticleCommande getArticleCommande() {
        return articleCommande;
    }

    public Long getSelectedCommandeId() {
        return selectedCommandeId;
    }

    public void setSelectedCommandeId(Long selectedCommandeId) {
        this.selectedCommandeId = selectedCommandeId;
    }

    public Long getSelectedProduitId() {
        return selectedProduitId;
    }

    public void setSelectedProduitId(Long selectedProduitId) {
        this.selectedProduitId = selectedProduitId;
    }

    public List<Commande> getAllCommandes() {
        return commandeFacade.findAll();
    }

    public List<Produit> getAllProduits() {
        return produitFacade.findAll();
    }

    public void save() {
        if(selectedCommandeId != null){
            articleCommande.setCommande(commandeFacade.find(selectedCommandeId));
        }
        if(selectedProduitId != null){
            articleCommande.setProduit(produitFacade.find(selectedProduitId));
        }

        System.out.println("Saving the articleCommande : " + articleCommande);
        this.articleCommandeFacade.create(articleCommande);

        this.message = "ArticleCommande saved successfully...";
        this.articleCommande = new ArticleCommande();
    }
}
