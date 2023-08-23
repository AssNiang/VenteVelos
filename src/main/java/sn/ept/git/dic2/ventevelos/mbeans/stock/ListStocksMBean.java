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

@Named("listStocksMbean")
@ViewScoped
public class ListStocksMBean implements Serializable {
    @EJB
    private StockFacade stockFacade;
    private Stock selectedStock;
    private List<Stock> stocks;
    @EJB
    private MagasinFacade magasinFacade;
    @EJB
    private ProduitFacade produitFacade;
    private Long selectedMagasinId;
    private Long selectedProduitId;

    public Stock getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(Stock selectedStock) {
        this.selectedStock = selectedStock;
    }

    public List<Stock> getStocks() {
        if(stocks == null){
            stocks = stockFacade.findAll();
        }
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
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

    public void edit() {
        if(selectedMagasinId != null){
            this.selectedStock.setMagasin(magasinFacade.find(selectedMagasinId));
        }
        if(selectedProduitId != null){
            this.selectedStock.setProduit(produitFacade.find(selectedProduitId));
        }

        System.out.println("Editing the stock : " + this.selectedStock);
        this.stockFacade.edit(this.selectedStock);
        this.refreshDisplay();
    }

    public void undo() {
        this.refreshDisplay();
    }

    public void delete(Stock stock) {
        System.out.println("Removing the stock : " + stock);
        this.stockFacade.remove(stock);
        this.refreshDisplay();
    }

    private void refreshDisplay() {
        // update the displayed rows
        stocks = stockFacade.findAll();
        this.selectedStock = null;
    }
}
