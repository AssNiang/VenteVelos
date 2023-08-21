package sn.ept.git.dic2.ventevelos.mbeans.produit;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Categorie;
import sn.ept.git.dic2.ventevelos.entities.Marque;
import sn.ept.git.dic2.ventevelos.entities.Produit;
import sn.ept.git.dic2.ventevelos.facades.CategorieFacade;
import sn.ept.git.dic2.ventevelos.facades.MarqueFacade;
import sn.ept.git.dic2.ventevelos.facades.ProduitFacade;

import java.io.Serializable;
import java.util.List;

@Named("listProduitsMbean")
@ViewScoped
public class ListProduitsMBean implements Serializable {
    @EJB
    private ProduitFacade produitFacade;
    private Produit selectedProduit;
    private List<Produit> produits;
    @EJB
    private CategorieFacade categorieFacade;
    @EJB
    private MarqueFacade marqueFacade;
    private Long selectedCategorieId;
    private Long selectedMarqueId;

    public Produit getSelectedProduit() {
        return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
        this.selectedProduit = selectedProduit;
    }

    public List<Produit> getProduits() {
        if(produits == null){
            produits = produitFacade.findAll();
        }
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Long getSelectedCategorieId() {
        return selectedCategorieId;
    }

    public void setSelectedCategorieId(Long selectedCategorieId) {
        this.selectedCategorieId = selectedCategorieId;
    }

    public Long getSelectedMarqueId() {
        return selectedMarqueId;
    }

    public void setSelectedMarqueId(Long selectedMarqueId) {
        this.selectedMarqueId = selectedMarqueId;
    }

    public List<Categorie> getAllCategories() {
        return categorieFacade.findAll();
    }

    public List<Marque> getAllMarques() {
        return marqueFacade.findAll();
    }

    public void edit() {
        // Load the existing Marque and Categorie instances based on selected IDs
        Categorie selectedCategorie = categorieFacade.find(selectedCategorieId);
        Marque selectedMarque = marqueFacade.find(selectedMarqueId);

        this.selectedProduit.setMarque(selectedMarque);
        this.selectedProduit.setCategorie(selectedCategorie);

        System.out.println("Editing the produit : " + this.selectedProduit);
        this.produitFacade.edit(this.selectedProduit);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Produit produit) {
        System.out.println("Removing the produit : " + produit);
        this.produitFacade.remove(produit);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        produits = produitFacade.findAll();
        this.selectedProduit = null;
    }
}
