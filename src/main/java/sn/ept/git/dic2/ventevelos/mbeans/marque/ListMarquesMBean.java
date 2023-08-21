package sn.ept.git.dic2.ventevelos.mbeans.marque;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Marque;
import sn.ept.git.dic2.ventevelos.facades.MarqueFacade;

import java.io.Serializable;
import java.util.List;

@Named("listMarquesMbean")
@ViewScoped
public class ListMarquesMBean implements Serializable {
    @EJB
    private MarqueFacade marqueFacade;
    private Marque selectedMarque;
    private List<Marque> marques;

    public Marque getSelectedMarque() {
        return selectedMarque;
    }

    public void setSelectedMarque(Marque selectedMarque) {
        this.selectedMarque = selectedMarque;
    }

    public List<Marque> getMarques() {
        if(marques == null){
            marques = marqueFacade.findAll();
        }
        return marques;
    }

    public void setMarques(List<Marque> marques) {
        this.marques = marques;
    }

    public void edit() {
        System.out.println("Editing the marque : " + this.selectedMarque);
        this.marqueFacade.edit(this.selectedMarque);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Marque marque) {
        System.out.println("Removing the marque : " + marque);
        this.marqueFacade.remove(marque);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        marques = marqueFacade.findAll();
        this.selectedMarque = null;
    }
}
