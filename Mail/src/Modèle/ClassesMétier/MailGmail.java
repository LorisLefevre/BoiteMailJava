package Modèle.ClassesMétier;

import Modèle.ClassesMétier.Utilisateur;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailGmail
{
    private String expediteur;
    private String password;
    private Session session;

    public MailGmail(String expediteur, String password)
    {
        this.expediteur = expediteur;
        this.password = password;
    }

    public void configurerSessionGmail()
    {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {

                return new PasswordAuthentication(expediteur, "puqv gfzz qdhb yomp");
            }
        });

        System.out.println("Session Gmail configurée pour l'utilisateur : " + expediteur);
    }

    // Getter pour récupérer la session SMTP
    public Session getSession()
    {
        return session;
    }


}
