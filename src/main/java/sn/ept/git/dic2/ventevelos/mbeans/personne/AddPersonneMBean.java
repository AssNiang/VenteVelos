package sn.ept.git.dic2.ventevelos.mbeans.personne;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Personne;
import sn.ept.git.dic2.ventevelos.facades.PersonneFacade;

import java.io.Serializable;

@Named("addPersonneMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddPersonneMBean implements Serializable {

    private String message;
    @EJB
    private PersonneFacade personneFacade;
    private Personne personne = new Personne(200l, "Ass", "NIANG");

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public void save() {
        System.out.println("Saving the personne : " + personne);
        this.personneFacade.create(personne);

        this.message = "Personne " + personne.getPrenom() + ' ' + personne.getNom() + " saved successfully...";
        this.personne = new Personne();
    }

}
