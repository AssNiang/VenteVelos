package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Client")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends Personne {
    private String telephone;
    @Email
    private String email;
    private String adresse;
    private String ville;
    private String etat;
    private String code_zip;


    public Client() {
        super();
    }

    public Client(Long id, String prenom, String nom, String telephone, String email, String adresse, String ville, String etat, String code_zip) {
        super(id, prenom, nom);
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.etat = etat;
        this.code_zip = code_zip;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCode_zip() {
        return code_zip;
    }

    public void setCode_zip(String code_zip) {
        this.code_zip = code_zip;
    }

    @Override
    public String toString() {
        return "Client{" +
                "personne='" + super.toString() + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", etat='" + etat + '\'' +
                ", code_zip='" + code_zip + '\'' +
                '}';
    }
}
