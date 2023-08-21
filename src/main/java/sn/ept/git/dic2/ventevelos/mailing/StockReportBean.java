package sn.ept.git.dic2.ventevelos.mailing;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class StockReportBean {
    private EmailBean emailBean = new EmailBean();
    @Schedule(hour = "*", minute = "20", second = "0", persistent = false)
    public void sendStockReport() {
        // Code pour générer le rapport d'état des stocks
        String stockReport = "Voici l'état des stocks des produits : ...";

        // Envoi du rapport par e-mail
        this.emailBean.sendEmail("nianga@ept.sn", "Rapport d'état des stocks", stockReport);
    }

}