package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ArticleCommande")
public class ArticleCommande implements Serializable {
    @Id
    private Long ligne;
    @Id
    @ManyToOne
    private Commande commande;
    @ManyToOne
    private Produit produit;
    private Integer quantite;
    private Double prix_depart;
    private Double remise;

    public ArticleCommande() {
    }

    public ArticleCommande(Long ligne, Commande commande, Produit produit, Integer quantite, Double prix_depart, Double remise) {
        this.ligne = ligne;
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.prix_depart = prix_depart;
        this.remise = remise;
    }

    public Long getLigne() {
        return ligne;
    }

    public void setLigne(Long ligne) {
        this.ligne = ligne;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getPrix_depart() {
        return prix_depart;
    }

    public void setPrix_depart(Double prix_depart) {
        this.prix_depart = prix_depart;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleCommande that = (ArticleCommande) o;
        return ligne.equals(that.ligne) && commande.equals(that.commande);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, commande);
    }

    @Override
    public String toString() {
        return "ArticleCommande{" +
                "ligne=" + ligne +
                ", commande=" + commande +
                ", produit=" + produit +
                ", quantite=" + quantite +
                ", prix_depart=" + prix_depart +
                ", remise=" + remise +
                '}';
    }
}
