package sn.ept.git.dic2.ventevelos.mbeans.magasin;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Magasin;
import sn.ept.git.dic2.ventevelos.facades.MagasinFacade;

import java.io.Serializable;

@Named("addMagasinMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddMagasinMBean implements Serializable {

    private String message;
    @EJB
    private MagasinFacade magasinFacade;
    private Magasin magasin = new Magasin(22l,"nom-magasin","771112233","magasin@ept.sn","PA-U25","Dakar","Senegal","SN");

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public void save() {
        System.out.println("Saving the magasin : " + magasin);
        this.magasinFacade.create(magasin);

        this.message = "Magasin " + magasin.getNom() + " saved successfully...";
        this.magasin = new Magasin();
    }

}
