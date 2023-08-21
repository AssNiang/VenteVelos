package sn.ept.git.dic2.ventevelos.mbeans.client;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Client;
import sn.ept.git.dic2.ventevelos.facades.ClientFacade;

import java.io.Serializable;

@Named("addClientMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddClientMBean implements Serializable {

    private String message;
    @EJB
    private ClientFacade clientFacade;
    private Client client = new Client(22l,"Ass Malick","NIANG","772914064","nianga@ept.sn","PA-U25","Dakar","Senegal","SN");

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void save() {
        System.out.println("Saving the client : " + client);
        this.clientFacade.create(client);

        this.message = "Client " + client.getPrenom() + ' ' + client.getNom() + " saved successfully...";
        this.client = new Client();
    }

}
