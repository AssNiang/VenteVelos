package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Magasin")
public class Magasin implements Serializable {
    @Id
    private Long id;
    private String nom;
    private String telephone;
    private String email;
    private String adresse;
    private String ville;
    private String etat;
    private String code_zip;

    public Magasin() {
    }

    public Magasin(Long id, String nom, String telephone, String email, String adresse, String ville, String etat, String code_zip) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.etat = etat;
        this.code_zip = code_zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magasin magasin = (Magasin) o;
        return id.equals(magasin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Magasin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", etat='" + etat + '\'' +
                ", code_zip='" + code_zip + '\'' +
                '}';
    }
}
