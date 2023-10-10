package sn.ept.git.dic2.ventevelos.mailing;

import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import sn.ept.git.dic2.ventevelos.entities.Stock;
import sn.ept.git.dic2.ventevelos.facades.StockFacade;

@Singleton
@Startup
public class StockReportBean {
    @EJB
    private StockFacade stockFacade;
    private EmailBean emailBean = new EmailBean();
    @Schedule(hour = "*", minute = "0", second = "0", persistent = false)
    public void sendStockReport() {
        // Code pour générer le rapport d'état des stocks
        String stockReport = "Voici l'état des stocks des produits : ...";
        for(Stock st: stockFacade.findAll()) {
            stockReport = stockReport.concat(
                    "\n---"
                    + "\tProduit: " + st.getProduit().getNom()
                    + "\tMagasin: " + st.getMagasin().getNom()
                    + "\tQuantité: " + st.getQuantite()
            );
        }

        // Envoi du rapport par e-mail
        this.emailBean.sendEmail("nianga@ept.sn", "Rapport d'état des stocks", stockReport);
    }

}