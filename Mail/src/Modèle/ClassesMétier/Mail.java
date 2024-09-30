package Modèle.ClassesMétier;

import Modèle.ClassesMétier.Utilisateur;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Mail
{
    private String expediteur;
    private String password;
    private Session session;

    public Mail(String expediteur, String password)
    {
        this.expediteur = expediteur;
        this.password = password;
    }

    public void ConfiguerSessionMail(String Hote)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", Hote);
        Session session = Session.getInstance(props, null);

        System.out.println("Session mail configurée pour l'utilisateur : " + expediteur);
    }

    public Session getSession()
    {
        return session;
    }

}
