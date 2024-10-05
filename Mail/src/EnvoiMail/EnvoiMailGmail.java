package EnvoiMail;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;

public class EnvoiMailGmail
{
    static String Hote = "gmail.com";

    public EnvoiMailGmail()
    {

    }

    public static void main(String[] args)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication("lorislfvlefevre@gmail.com", "puqv gfzz qdhb yomp");
        }
        });
        try {
            String Expediteur = "lorislfvlefevre@gmail.com";
            String Destinataire = "lorislfvlefevre@gmail.com";
            String Sujet = "Test Mail Java";
            String Message = "Bonjour, ceci est un test d'envoi de mail avec Java.";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Expediteur));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Destinataire));
            message.setSubject(Sujet);
            message.setText(Message);

            System.out.println("Envoi du mail...");

            javax.mail.Transport.send(message);

            System.out.println("Envoi reussi !");
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de l'envoi du mail : " + e);
        }
    }
}
