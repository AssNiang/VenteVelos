package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Stock")
public class Stock implements Serializable {
    @Id
    @ManyToOne
    private Magasin magasin;
    @Id
    @ManyToOne
    private Produit produit;
    private Integer quantite;

    // Constructors

    public Stock() {
    }

    public Stock(Magasin magasin, Produit produit, Integer quantite) {
        this.magasin = magasin;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return magasin.equals(stock.magasin) && produit.equals(stock.produit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magasin, produit);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "magasin=" + magasin +
                ", produit=" + produit +
                ", quantite=" + quantite +
                '}';
    }
}