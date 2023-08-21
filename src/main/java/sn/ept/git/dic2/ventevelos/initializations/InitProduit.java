//package sn.ept.git.dic2.ventevelos.initializations;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.ejb.EJB;
//import jakarta.ejb.Singleton;
//import jakarta.ejb.Startup;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import sn.ept.git.dic2.ventevelos.entities.Produit;
//import sn.ept.git.dic2.ventevelos.facades.ProduitFacade;
//
//
//@Singleton
//@Startup
//public class InitProduit {
//    @PersistenceContext(name = "assPU")
//    private EntityManager entityManager;
//    @EJB
//    private ProduitFacade produitFacade;
//
//    @PostConstruct
//    public void init() {
//        System.out.println("### Initializing Produit Entities");
//
//
//        if (produitFacade.count()>0){
//            return;
//        }
//
//        Integer[] numeros = new Integer[]{1, 2, 3};
//        String[] produitNoms = new String[]{"Trek 820 - 2016", "Ritchey Timberwolf Frameset - 2016", "Surly Wednesday Frameset - 2016"};
//        Integer[] marqueIds = new Integer[]{9, 5, 8};
//        Integer[] categorieIds = new Integer[]{6, 6, 6};
//        Long[] anneeModels = new Long[]{2016L, 2016L, 2016L};
//        Double[] prixDeparts = new Double[]{379.99, 749.99, 999.99};
//
//        for (int i = 0; i < numeros.length; i++) {
//            Integer numero = numeros[i];
//            String produitNom = produitNoms[i];
//            Integer marqueId = marqueIds[i];
//            Integer categorieId = categorieIds[i];
//            Long anneeModel = anneeModels[i];
//            Double prixDepart = prixDeparts[i];
//
//            Produit produit = new Produit(numero, produitNom, marqueId, categorieId, anneeModel, prixDepart);
//
//            produitFacade.create(produit);
//        }
//    }
//}
