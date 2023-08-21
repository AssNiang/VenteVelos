package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Produit")
public class Produit implements Serializable {
    @Id
    private Long id;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    private Long annee_model;
    private Double prix_depart;

    // Constructors

    public Produit() {
    }

    public Produit(Long id, String nom, Marque marque, Categorie categorie, Long annee_model, Double prix_depart) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.categorie = categorie;
        this.annee_model = annee_model;
        this.prix_depart = prix_depart;
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

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getAnnee_model() {
        return annee_model;
    }

    public void setAnnee_model(Long annee_model) {
        this.annee_model = annee_model;
    }

    public Double getPrix_depart() {
        return prix_depart;
    }

    public void setPrix_depart(Double prix_depart) {
        this.prix_depart = prix_depart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id.equals(produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque=" + marque +
                ", categorie=" + categorie +
                ", annee_model=" + annee_model +
                ", prix_depart=" + prix_depart +
                '}';
    }
}
