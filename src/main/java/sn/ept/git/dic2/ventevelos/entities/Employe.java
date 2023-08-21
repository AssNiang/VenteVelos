package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Employe")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe extends Client {
    private Integer actif;
    @ManyToOne
    private Magasin magasin;
    @ManyToOne
    private Employe manager;


    public Employe() {
        super();
    }

    public Employe(Long id, String prenom, String nom, String telephone, String email, String adresse, String ville, String etat, String code_zip, Integer actif, Magasin magasin, Employe manager) {
        super(id, prenom, nom, telephone, email, adresse, ville, etat, code_zip);
        this.actif = actif;
        this.magasin = magasin;
        this.manager = manager;
    }

    public Integer getActif() {
        return actif;
    }

    public void setActif(Integer actif) {
        this.actif = actif;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Employe getManager() {
        return manager;
    }

    public void setManager(Employe manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "client=" + super.toString() +
                ", actif=" + actif +
                ", magasin=" + magasin +
                ", manager=" + manager +
                '}';
    }
}
