package EnvoiMail;

import com.sun.jdi.connect.Transport;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;

public class EnvoiMail
{
    static String Hote = "u4.tech.hepl.local";

    public EnvoiMail()
    {

    }

    public static void main(String[] args)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", Hote);
        Session session = Session.getDefaultInstance(props, null);
        try
        {
            String Expediteur = "lefevrelo@u4.tech.hepl.local";
            String Destinataire = "lefevrelo@u4.tech.hepl.local";
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
