package sn.ept.git.dic2.ventevelos.initializations;//package sn.ept.git.dic2.ventevelos.initializations;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventevelos.entities.Personne;
import sn.ept.git.dic2.ventevelos.facades.PersonneFacade;

import java.util.Random;

@Singleton
@Startup
public class InitPersonne {
    @PersistenceContext(name = "assPU")
    private EntityManager entityManager;
    @EJB
    private PersonneFacade personneFacade;

    @PostConstruct
    public void init() {
        System.out.println("### Initializing Personne Entities");


        if (personneFacade.count()>0){
            return;
        }

        Long[] personneIds = {1l, 2l, 3l};
        String[] personneNoms = {"NIANG", "MBENGUE", "DIAGNE"};
        String[] personnePrenoms = {"Ass", "Serigne Fallou", "Al Amine"};

        for (int i = 0; i < personneIds.length; i++) {
            Long personneId = personneIds[i];
            String personneNom = personneNoms[i];
            String personnePrenom = personnePrenoms[i];

            Personne personne = new Personne(personneId,personnePrenom,personneNom);
            personneFacade.create(personne);
        }
    }
}
