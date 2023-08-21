package sn.ept.git.dic2.ventevelos.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Commande")
public class Commande implements Serializable {
    @Id
    private Long numero;
    @ManyToOne
    private Client client;
    private Integer statut;
    private Date date_commande;
    private Date date_livraison;
    @ManyToOne
    private Magasin magasin;
    @ManyToOne
    private Employe employe;

    public Commande() {
    }

    public Commande(Long numero, Client client, Integer statut, Date date_commande, Date date_livraison, Magasin magasin, Employe employe) {
        this.numero = numero;
        this.client = client;
        this.statut = statut;
        this.date_commande = date_commande;
        this.date_livraison = date_livraison;
        this.magasin = magasin;
        this.employe = employe;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return numero.equals(commande.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numero=" + numero +
                ", client=" + client +
                ", statut=" + statut +
                ", date_commande=" + date_commande +
                ", date_livraison=" + date_livraison +
                ", magasin=" + magasin +
                ", employe=" + employe +
                '}';
    }
}
