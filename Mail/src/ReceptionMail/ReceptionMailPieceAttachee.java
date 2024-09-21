package ReceptionMail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

public class ReceptionMailPieceAttachee
{
    static String Hote = "u4.tech.hepl.local";
    static String charset = "iso8859-1";

    public ReceptionMailPieceAttachee()
    {

    }

    public static void main(String[] args)
    {
        Properties props = System.getProperties();
        props.put("mail.pop3.host", Hote);
        props.put("mail.disable.top", "true");
        props.put("file.encoding", charset);
        System.out.println("Création d'une session mail");
        Session session = Session.getDefaultInstance(props, null);
        try
        {
            String user = "lefevrelo";
            String password = "azerty1234";
            Store store = session.getStore("pop3");
            store.connect(Hote, user, password);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message messages []= folder.getMessages();
            System.out.println("Nombre de messages : " + folder.getMessageCount());
            System.out.println("Nombre de nouveaux messages : " + folder.getNewMessageCount());

            System.out.println("Liste des messages");
            for(int i = 0; i < messages.length; i++)
            {
                //String[] rt = messages[i].getHeader("Return-Path");
                //String[] rt = messages[i].getHeader("From");
                String[] rt = messages[i].getHeader("Subject");
                System.out.println("Bonjour" + rt[0] + "Bonjour");
                int pos = rt[0].indexOf("TextMail");
                System.out.println("Bonjour" + pos + "Bonjour");
            }

            for(int i = 0; i < messages.length; i++)
            {
                System.out.println("Message n° " + i);
                System.out.println("Expediteur : " + messages[i].getFrom()[0]);
                System.out.println("Sujet : " + messages[i].getSubject());
                System.out.println("Date : " + messages[i].getSentDate());
            }


        }

        catch (Exception e)
        {
            System.out.println("Erreur lors de la réception du mail : " + e);
        }
    }
}
