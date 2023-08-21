package sn.ept.git.dic2.ventevelos.mbeans.client;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Client;
import sn.ept.git.dic2.ventevelos.facades.ClientFacade;

import java.io.Serializable;
import java.util.List;

@Named("listClientsMbean")
@ViewScoped
public class ListClientsMBean implements Serializable {
    @EJB
    private ClientFacade clientFacade;
    private Client selectedClient;
    private List<Client> clients;

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public List<Client> getClients() {
        if(clients == null){
            clients = clientFacade.findAll();
        }
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void edit() {
        System.out.println("Editing the client : " + this.selectedClient);
        this.clientFacade.edit(this.selectedClient);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Client client) {
        System.out.println("Removing the client : " + client);
        this.clientFacade.remove(client);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        clients = clientFacade.findAll();
        this.selectedClient = null;
    }
}
