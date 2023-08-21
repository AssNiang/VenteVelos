package sn.ept.git.dic2.ventevelos.mbeans.employe;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Employe;
import sn.ept.git.dic2.ventevelos.entities.Magasin;
import sn.ept.git.dic2.ventevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventevelos.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("listEmployesMbean")
@ViewScoped
public class ListEmployesMBean implements Serializable {
    @EJB
    private EmployeFacade employeFacade;
    private Employe selectedEmploye;
    private List<Employe> employes;
    @EJB
    private MagasinFacade magasinFacade;
    private Long selectedEmployeId;
    private Long selectedMagasinId;

    public Employe getSelectedEmploye() {
        return selectedEmploye;
    }

    public void setSelectedEmploye(Employe selectedEmploye) {
        this.selectedEmploye = selectedEmploye;
    }

    public List<Employe> getEmployes() {
        if(employes == null){
            employes = employeFacade.findAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public Long getSelectedEmployeId() {
        return selectedEmployeId;
    }

    public void setSelectedEmployeId(Long selectedEmployeId) {
        this.selectedEmployeId = selectedEmployeId;
    }

    public Long getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Long selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }
    public List<Magasin> getAllMagasins() {
        return this.magasinFacade.findAll();
    }

    public List<Employe> getAllEmployes() {
        return this.employeFacade.findAll();
    }

    public void edit() {
        if(selectedMagasinId != null){
            this.selectedEmploye.setMagasin(magasinFacade.find(selectedMagasinId));
        }
        if(selectedEmployeId != null){
            this.selectedEmploye.setManager(employeFacade.find(selectedEmployeId));
        }

        System.out.println("Editing the employe : " + this.selectedEmploye);
        this.employeFacade.edit(this.selectedEmploye);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Employe employe) {
        System.out.println("Removing the employe : " + employe);
        this.employeFacade.remove(employe);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        employes = employeFacade.findAll();
        this.selectedEmploye = null;
    }
}
