package sn.ept.git.dic2.ventevelos.mbeans.commande;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Employe;
import sn.ept.git.dic2.ventevelos.entities.Magasin;
import sn.ept.git.dic2.ventevelos.entities.Client;
import sn.ept.git.dic2.ventevelos.entities.Commande;
import sn.ept.git.dic2.ventevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventevelos.facades.MagasinFacade;
import sn.ept.git.dic2.ventevelos.facades.ClientFacade;
import sn.ept.git.dic2.ventevelos.facades.CommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("listCommandesMbean")
@ViewScoped
public class ListCommandesMBean implements Serializable {
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private MagasinFacade magasinFacade;
    @EJB
    private ClientFacade clientFacade;
    @EJB
    private EmployeFacade employeFacade;

    private Long selectedMagasinId;
    private Long selectedClientId;
    private Long selectedEmployeId;

    private Commande selectedCommande;
    private List<Commande> commandes;

    public Commande getSelectedCommande() {
        return selectedCommande;
    }

    public void setSelectedCommande(Commande selectedCommande) {
        this.selectedCommande = selectedCommande;
    }

    public List<Commande> getCommandes() {
        if(commandes == null){
            commandes = commandeFacade.findAll();
        }
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public Long getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Long selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public Long getSelectedClientId() {
        return selectedClientId;
    }

    public void setSelectedClientId(Long selectedClientId) {
        this.selectedClientId = selectedClientId;
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


    public void edit() {
        if(selectedMagasinId != null){
            this.selectedCommande.setMagasin(magasinFacade.find(selectedMagasinId));
        }
        if(selectedClientId != null){
            this.selectedCommande.setClient(clientFacade.find(selectedClientId));
        }
        if(selectedEmployeId != null){
            this.selectedCommande.setEmploye(employeFacade.find(selectedEmployeId));
        }

        System.out.println("Editing the commande : " + this.selectedCommande);
        this.commandeFacade.edit(this.selectedCommande);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Commande commande) {
        System.out.println("Removing the commande : " + commande);
        this.commandeFacade.remove(commande);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        commandes = commandeFacade.findAll();
        this.selectedCommande = null;
    }
}
