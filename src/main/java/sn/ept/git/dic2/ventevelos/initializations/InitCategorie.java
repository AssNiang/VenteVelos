package sn.ept.git.dic2.ventevelos.initializations;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Categorie;
import sn.ept.git.dic2.ventevelos.facades.CategorieFacade;
import java.util.Random;

@Singleton
@Startup
public class InitCategorie {
//    @PersistenceContext(name = "assPU")
//    private EntityManager entityManager;
    @EJB
    private CategorieFacade categorieFacade;

    @PostConstruct
    public void init() {
        System.out.println("### Initializing Categorie Entities");


        if (categorieFacade.count()>0){
            return;
        }
        System.out.println(categorieFacade.findAll());
//        String[] categorieNoms = {"Children Bicycles", "Comfort Bicycles", "Electric Bikes"};
//
//        for (int i = 0; i < categorieNoms.length; i++) {
//            String categorieNom = categorieNoms[i];
//            Integer numero = (i+1);
//
//            Categorie categorie = new Categorie(numero,categorieNom);
//            categorieFacade.create(categorie);
//        }
    }
}
