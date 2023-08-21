package sn.ept.git.dic2.ventevelos.mbeans.commande;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Client;
import sn.ept.git.dic2.ventevelos.entities.Commande;
import sn.ept.git.dic2.ventevelos.entities.Employe;
import sn.ept.git.dic2.ventevelos.entities.Magasin;
import sn.ept.git.dic2.ventevelos.facades.ClientFacade;
import sn.ept.git.dic2.ventevelos.facades.CommandeFacade;
import sn.ept.git.dic2.ventevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventevelos.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("addCommandeMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddCommandeMBean implements Serializable {

    private String message;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private MagasinFacade magasinFacade;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private EmployeFacade employeFacade;

    private Long selectedClientId;
    private Long selectedMagasinId;
    private Long selectedEmployeId;

    private Commande commande = new Commande();

    public AddCommandeMBean() {
        commande.setNumero(1L);
        commande.setStatut(1);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Long getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(Long selectedClientId) {
        this.selectedClientId = selectedClientId;
    }

    public Long getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Long selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public Long getSelectedEmployeId() {
        return selectedEmployeId;
    }

    public void setSelectedEmployeId(Long selectedEmployeId) {
        this.selectedEmployeId = selectedEmployeId;
    }


    public List<Magasin> getAllMagasins() {
        return this.magasinFacade.findAll();
    }

    public List<Client> getAllClients() {
        return this.clientFacade.findAll();
    }

    public List<Employe> getAllEmployes() {
        return this.employeFacade.findAll();
    }


    public void save() {
        if(selectedMagasinId != null){
            commande.setMagasin(magasinFacade.find(selectedMagasinId));
        }
        if(selectedClientId != null){
            commande.setClient(clientFacade.find(selectedClientId));
        }
        if(selectedEmployeId != null){
            commande.setEmploye(employeFacade.find(selectedEmployeId));
        }

        System.out.println("Saving the commande : " + commande);
        this.commandeFacade.create(commande);

        this.message = "Commande number " + commande.getNumero() + " saved successfully...";
        this.commande = new Commande();
    }

}
