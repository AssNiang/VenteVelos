package sn.ept.git.dic2.ventevelos.mbeans.stock;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.ventevelos.entities.Stock;
import sn.ept.git.dic2.ventevelos.entities.Magasin;
import sn.ept.git.dic2.ventevelos.entities.Produit;
import sn.ept.git.dic2.ventevelos.facades.StockFacade;
import sn.ept.git.dic2.ventevelos.facades.MagasinFacade;
import sn.ept.git.dic2.ventevelos.facades.ProduitFacade;

import java.io.Serializable;
import java.util.List;

@Named("addStockMBean")
@ViewScoped
public class AddStockMBean implements Serializable {

    private String message;
    @EJB
    private StockFacade stockFacade;
    @EJB
    private MagasinFacade magasinFacade;
    @EJB
    private ProduitFacade produitFacade;

    private Stock stock = new Stock();
    private Long selectedMagasinId;
    private Long selectedProduitId;

    public AddStockMBean() {
        stock.setQuantite(10);
    }

    public String getMessage() {
        return message;
    }

    public Stock getStock() {
        return stock;
    }

    public Long getSelectedMagasinId() {
        return selectedMagasinId;
    }

    public void setSelectedMagasinId(Long selectedMagasinId) {
        this.selectedMagasinId = selectedMagasinId;
    }

    public Long getSelectedProduitId() {
        return selectedProduitId;
    }

    public void setSelectedProduitId(Long selectedProduitId) {
        this.selectedProduitId = selectedProduitId;
    }

    public List<Magasin> getAllMagasins() {
        return magasinFacade.findAll();
    }

    public List<Produit> getAllProduits() {
        return produitFacade.findAll();
    }

    public void save() {
        if(selectedMagasinId != null){
            stock.setMagasin(magasinFacade.find(selectedMagasinId));
        }
        if(selectedProduitId != null){
            stock.setProduit(produitFacade.find(selectedProduitId));
        }

        System.out.println("Saving the stock : " + stock);
        this.stockFacade.create(stock);

        this.message = "Stock saved successfully...";
        this.stock = new Stock();
    }
}
