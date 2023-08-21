package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Personne")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {
    @Id
    private Long id;
    private String prenom;
    private String nom;



    // Constructors

    public Personne() {
    }

    public Personne(Long id, String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return id.equals(personne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
