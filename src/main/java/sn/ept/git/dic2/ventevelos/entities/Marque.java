package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Marque")
public class Marque implements Serializable {
    @Id
    private Long id;
    private String nom;


    // Constructors

    public Marque() {
    }

    public Marque(Long id, String nom) {
        this.id = id;
        this.nom = nom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return id.equals(marque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Marque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
