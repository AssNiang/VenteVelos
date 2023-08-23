package sn.ept.git.dic2.ventevelos.mbeans.articleCommande;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Commande;
import sn.ept.git.dic2.ventevelos.entities.Produit;
import sn.ept.git.dic2.ventevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventevelos.facades.CommandeFacade;
import sn.ept.git.dic2.ventevelos.facades.ProduitFacade;
import sn.ept.git.dic2.ventevelos.facades.ArticleCommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("listArticleCommandesMbean")
@ViewScoped
public class ListArticleCommandesMBean implements Serializable {
    @EJB
    private ArticleCommandeFacade articleCommandeFacade;
    private ArticleCommande selectedArticleCommande;
    private List<ArticleCommande> articleCommandes;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private ProduitFacade produitFacade;
    private Long selectedCommandeId;
    private Long selectedProduitId;

    public ArticleCommande getSelectedArticleCommande() {
        return selectedArticleCommande;
    }

    public void setSelectedArticleCommande(ArticleCommande selectedArticleCommande) {
        this.selectedArticleCommande = selectedArticleCommande;
    }

    public List<ArticleCommande> getArticleCommandes() {
        if(articleCommandes == null){
            articleCommandes = articleCommandeFacade.findAll();
        }
        return articleCommandes;
    }

    public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
        this.articleCommandes = articleCommandes;
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

    public void edit() {
        if(selectedCommandeId != null){
            this.selectedArticleCommande.setCommande(commandeFacade.find(selectedCommandeId));
        }
        if(selectedProduitId != null){
            this.selectedArticleCommande.setProduit(produitFacade.find(selectedProduitId));
        }

        System.out.println("Editing the articleCommande : " + this.selectedArticleCommande);
        this.articleCommandeFacade.edit(this.selectedArticleCommande);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(ArticleCommande articleCommande) {
        System.out.println("Removing the articleCommande : " + articleCommande);
        this.articleCommandeFacade.remove(articleCommande);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        articleCommandes = articleCommandeFacade.findAll();
        this.selectedArticleCommande = null;
    }
}
