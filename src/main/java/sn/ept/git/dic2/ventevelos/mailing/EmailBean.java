package sn.ept.git.dic2.ventevelos.mailing;

import jakarta.ejb.Stateless;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class EmailBean {

    public void sendEmail(String recipient, String subject, String messageBody) {
        // Paramètres d'accès au serveur SMTP
        String host = "mail.galgit.com"; // Remplacez par l'hôte SMTP approprié
        String username = "dic2-2023@galgit.com"; // Remplacez par votre nom d'utilisateur SMTP
        String password = "sn-ept@GIT2024"; // Remplacez par votre mot de passe SMTP

        // Paramètres de configuration pour la session de messagerie
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Port SMTP standard pour TLS

        // Création de la session de messagerie avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            // Envoi du message
            Transport.send(message);

            System.out.println("Le message a été envoyé avec succès à " + recipient);
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi du message : " + e.getMessage());
        }
    }
}
