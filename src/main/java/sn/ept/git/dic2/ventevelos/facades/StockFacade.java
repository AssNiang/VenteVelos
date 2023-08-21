package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Stock;

@Stateless
public class StockFacade extends AbstractFacade<Stock>{

    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;

    public StockFacade() {
        super(Stock.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
