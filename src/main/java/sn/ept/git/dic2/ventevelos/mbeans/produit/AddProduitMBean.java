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

@Named("addProduitMBean")
@ViewScoped
public class AddProduitMBean implements Serializable {

    private String message;
    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private CategorieFacade categorieFacade;
    @EJB
    private MarqueFacade marqueFacade;

    private Produit produit = new Produit();
    private Long selectedCategorieId;
    private Long selectedMarqueId;

    public AddProduitMBean() {;
        produit.setId(1l);
        produit.setNom("nom produit");
        produit.setAnnee_model(2023l);
        produit.setPrix_depart(10.);
    }

    public String getMessage() {
        return message;
    }

    public Produit getProduit() {
        return produit;
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

    public void save() {
        // Load the existing Marque and Categorie instances based on selected IDs
        Categorie selectedCategorie = categorieFacade.find(selectedCategorieId);
        Marque selectedMarque = marqueFacade.find(selectedMarqueId);

        // Set the existing Marque and Categorie instances in the produit
        produit.setMarque(selectedMarque);
        produit.setCategorie(selectedCategorie);

        System.out.println("Saving the produit : " + produit);
        this.produitFacade.create(produit);

        this.message = "Produit saved successfully...";
        this.produit = new Produit();
    }
}
