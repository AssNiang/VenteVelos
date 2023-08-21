//package sn.ept.git.dic2.ventevelos.initializations;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.ejb.EJB;
//import jakarta.ejb.Singleton;
//import jakarta.ejb.Startup;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import sn.ept.git.dic2.ventevelos.entities.Marque;
//import sn.ept.git.dic2.ventevelos.facades.MarqueFacade;
//
//@Singleton
//@Startup
//public class InitMarque {
//    @PersistenceContext(name = "assPU")
//    private EntityManager entityManager;
//    @EJB
//    private MarqueFacade marqueFacade;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("### Initializing Marque Entities");
//
//
//        if (marqueFacade.count()>0){
//            return;
//        }
//
//        Integer[] numeros = new Integer[]{1, 2, 3};
//        String[] marqueNoms = {"Electra", "Haro", "Heller"};
//
//        for (int i = 0; i < marqueNoms.length; i++) {
//            Integer numero = numeros[i];
//            String marqueNom = marqueNoms[i];
//
//            Marque marque = new Marque(numero,marqueNom);
//            marqueFacade.create(marque);
//        }
//    }
//}
