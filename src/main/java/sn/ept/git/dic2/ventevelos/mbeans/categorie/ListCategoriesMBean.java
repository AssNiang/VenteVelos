package sn.ept.git.dic2.ventevelos.mbeans.categorie;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Categorie;
import sn.ept.git.dic2.ventevelos.facades.CategorieFacade;

import java.io.Serializable;
import java.util.List;

@Named("listCategoriesMbean")
@ViewScoped
public class ListCategoriesMBean implements Serializable {
    @EJB
    private CategorieFacade categorieFacade;
    private Categorie selectedCategorie;
    private List<Categorie> categories;

    public Categorie getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(Categorie selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
    }

    public List<Categorie> getCategories() {
        if(categories == null){
            categories = categorieFacade.findAll();
        }
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public void setCategorieToEdit(Categorie categorieToEdit) {
        // Implement the logic to handle editing the person
        // You might navigate to an edit page or set properties to control the edit flow
        this.selectedCategorie = categorieToEdit;
        System.out.println("----" + categorieToEdit);
    }

    public void edit() {
        System.out.println("Editing the categorie : " + this.selectedCategorie);
        this.categorieFacade.edit(this.selectedCategorie);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Categorie categorie) {
        System.out.println("Removing the categorie : " + categorie);
        this.categorieFacade.remove(categorie);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        categories = categorieFacade.findAll();
        this.selectedCategorie = null;
    }
}
