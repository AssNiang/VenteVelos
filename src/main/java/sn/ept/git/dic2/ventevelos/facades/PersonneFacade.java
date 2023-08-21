package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Personne;

@Stateless
public class PersonneFacade extends AbstractFacade<Personne>{

    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;

    public PersonneFacade() {
        super(Personne.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
