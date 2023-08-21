package sn.ept.git.dic2.ventevelos.mbeans.marque;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Marque;
import sn.ept.git.dic2.ventevelos.facades.MarqueFacade;

import java.io.Serializable;

@Named("addMarqueMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddMarqueMBean implements Serializable {

    private String message;
    @EJB
    private MarqueFacade marqueFacade;
    private Marque marque = new Marque(1l,"Haro");

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public void save() {
        System.out.println("Saving the marque : " + marque);
        this.marqueFacade.create(marque);

        this.message = "Marque " + marque.getNom() + " saved successfully...";
        this.marque = new Marque();
    }

}
