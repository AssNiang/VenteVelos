package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Commande;

@Stateless
public class CommandeFacade extends AbstractFacade<Commande>{

    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;

    public CommandeFacade() {
        super(Commande.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
