package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Employe;

@Stateless
public class EmployeFacade extends AbstractFacade<Employe>{

    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;

    public EmployeFacade() {
        super(Employe.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
