package sn.ept.git.dic2.ventevelos.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "API FOR VENTE_VELOS PROJECT",
                description = "Projet de matière du cours de Jakarta EE et Services Web (https://drive.google.com/file/d/1pOHxjmCiNisepr9KjTWHS-YxHcGAcKcb/view?usp=sharing)",
                contact = @Contact(
                        name = "Ass",
                        email = "nianga@ept.sn",
                        url = "link-to-my-website"
                ),
                version = "1.0",
                license = @License(name = "OpenSource")
        ),
        servers = {@Server(
                url = "{urlDeployment}",
                variables = {
                        @ServerVariable(
                                name = "urlDeployment",
                                defaultValue = "http://localhost:8080/VenteVelos-1.0-SNAPSHOT"
                        )
                }
        )}
)
public class ApiRestConfig extends Application {
}
