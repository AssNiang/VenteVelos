package sn.ept.git.dic2.ventevelos.mbeans.magasin;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Magasin;
import sn.ept.git.dic2.ventevelos.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("listMagasinsMbean")
@ViewScoped
public class ListMagasinsMBean implements Serializable {
    @EJB
    private MagasinFacade magasinFacade;
    private Magasin selectedMagasin;
    private List<Magasin> magasins;

    public Magasin getSelectedMagasin() {
        return selectedMagasin;
    }

    public void setSelectedMagasin(Magasin selectedMagasin) {
        this.selectedMagasin = selectedMagasin;
    }

    public List<Magasin> getMagasins() {
        if(magasins == null){
            magasins = magasinFacade.findAll();
        }
        return magasins;
    }

    public void setMagasins(List<Magasin> magasins) {
        this.magasins = magasins;
    }

    public void edit() {
        System.out.println("Editing the magasin : " + this.selectedMagasin);
        this.magasinFacade.edit(this.selectedMagasin);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Magasin magasin) {
        System.out.println("Removing the magasin : " + magasin);
        this.magasinFacade.remove(magasin);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        magasins = magasinFacade.findAll();
        this.selectedMagasin = null;
    }
}
