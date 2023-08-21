//package sn.ept.git.dic2.ventevelos.initializations;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.ejb.EJB;
//import jakarta.ejb.Singleton;
//import jakarta.ejb.Startup;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import sn.ept.git.dic2.ventevelos.entities.Stock;
//import sn.ept.git.dic2.ventevelos.facades.StockFacade;
//
//
//@Singleton
//@Startup
//public class InitStock {
//    @PersistenceContext(name = "assPU")
//    private EntityManager entityManager;
//    @EJB
//    private StockFacade stockFacade;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("### Initializing Stock Entities");
//
//
//        if (stockFacade.count()>0){
//            return;
//        }
//
//        Integer[] magasinIds = new Integer[]{1, 1, 1};
//        Integer[] produitIds = new Integer[]{1, 2, 3};
//        Integer[] quantites = new Integer[]{27, 5, 6};
//
//        for (int i = 0; i < magasinIds.length; i++) {
//            Integer magasinId = magasinIds[i];
//            Integer produitId = produitIds[i];
//            Integer quantite = quantites[i];
//
//            Stock stock = new Stock(magasinId, produitId, quantite);
//
//            stockFacade.create(stock);
//        }
//    }
//}
