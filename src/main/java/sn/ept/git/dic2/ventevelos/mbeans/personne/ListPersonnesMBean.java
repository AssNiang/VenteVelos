package sn.ept.git.dic2.ventevelos.mbeans.personne;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Personne;
import sn.ept.git.dic2.ventevelos.facades.PersonneFacade;

import java.io.Serializable;
import java.util.List;

@Named("listPersonnesMbean")
@ViewScoped
public class ListPersonnesMBean implements Serializable {
    @EJB
    private PersonneFacade personneFacade;
    private Personne selectedPersonne;
    private List<Personne> personnes;

    public Personne getSelectedPersonne() {
        return selectedPersonne;
    }

    public void setSelectedPersonne(Personne selectedPersonne) {
        this.selectedPersonne = selectedPersonne;
    }

    public List<Personne> getPersonnes() {
        if(personnes == null){
            personnes = personneFacade.findAll();
        }
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public void edit() {
        System.out.println("Editing the personne : " + this.selectedPersonne);
        this.personneFacade.edit(this.selectedPersonne);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Personne personne) {
        System.out.println("Removing the personne : " + personne);
        this.personneFacade.remove(personne);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        personnes = personneFacade.findAll();
        this.selectedPersonne = null;
    }
}
