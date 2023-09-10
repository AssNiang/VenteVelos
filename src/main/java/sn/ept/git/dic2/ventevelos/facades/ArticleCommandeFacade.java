package sn.ept.git.dic2.ventevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventevelos.entities.Stock;

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

    public ArticleCommande find(Object ligne, Object numCommande) {
        return super.findAll().stream()
                .filter(articleCommande -> ligne.equals(articleCommande.getLigne()) && numCommande.equals(articleCommande.getCommande().getNumero()))
                .findAny()
                .orElse(null);
    }
}
