package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Produit;

@Stateless
public class ProduitFacade extends AbstractFacade<Produit>{

    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;

    public ProduitFacade() {
        super(Produit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
