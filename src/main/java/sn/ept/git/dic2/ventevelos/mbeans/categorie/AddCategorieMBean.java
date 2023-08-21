package sn.ept.git.dic2.ventevelos.mbeans.categorie;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Categorie;
import sn.ept.git.dic2.ventevelos.facades.CategorieFacade;

import java.io.Serializable;

@Named("addCategorieMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddCategorieMBean implements Serializable {

    private String message;
    @EJB
    private CategorieFacade categorieFacade;
    private Categorie categorie = new Categorie(1l,"Children Bicycles");

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void save() {
        System.out.println("Saving the categorie : " + categorie);
        this.categorieFacade.create(categorie);

        this.message = "Categorie " + categorie.getNom() + " saved successfully...";
        this.categorie = new Categorie();
    }

}
