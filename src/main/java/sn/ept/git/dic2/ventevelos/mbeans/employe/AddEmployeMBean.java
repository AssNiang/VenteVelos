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

@Named("addEmployeMBean")
@ViewScoped // the object will be deleted if the page is changed.
public class AddEmployeMBean implements Serializable {

    private String message;
    @EJB
    private EmployeFacade employeFacade;
    @EJB
    private MagasinFacade magasinFacade;
    private Employe employe = new Employe();
    private Long selectedEmployeId;
    private Long selectedMagasinId;

    public AddEmployeMBean() {
        this.initEmploye();
    }

    private void initEmploye(){
        employe.setId(25L);
        employe.setPrenom("Ousmane");
        employe.setNom("DIOP");
        employe.setTelephone("771234567");
        employe.setEmail("ousmane@ept.sn");
        employe.setAdresse("PA-U26");
        employe.setVille("Dakar");
        employe.setEtat("Senegal");
        employe.setCode_zip("SN");
        employe.setActif(25);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
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

    public void save() {
        if(selectedMagasinId != null){
            employe.setMagasin(magasinFacade.find(selectedMagasinId));
        }
        if(selectedEmployeId != null){
            employe.setManager(employeFacade.find(selectedEmployeId));
        }

        System.out.println("Saving the employe : " + employe);
        this.employeFacade.create(employe);

        this.message = "Employe " + employe.getPrenom() + ' ' + employe.getNom() + " saved successfully...";
        this.employe = new Employe();
    }

}
