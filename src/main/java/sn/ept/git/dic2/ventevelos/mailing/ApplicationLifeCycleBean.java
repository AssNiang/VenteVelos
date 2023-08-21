package sn.ept.git.dic2.ventevelos.mailing;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class ApplicationLifeCycleBean {
    private EmailBean emailBean = new EmailBean();

    @PostConstruct
    public void startup() {
        // Code exécuté au démarrage de l'application
        this.emailBean.sendEmail("nianga@ept.sn", "Application démarrée", "L'application a été démarrée.");
    }

    @PreDestroy
    public void shutdown() {
        // Code exécuté à l'arrêt de l'application
        this.emailBean.sendEmail("nianga@ept.sn", "Application arrêtée", "L'application a été arrêtée.");
    }
}