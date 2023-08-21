package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.ArticleCommande;

@Stateless
public class ArticleCommandeFacade extends AbstractFacade<ArticleCommande>{

    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;

    public ArticleCommandeFacade() {
        super(ArticleCommande.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
