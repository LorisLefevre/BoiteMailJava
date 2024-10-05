package ReceptionMail;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;
public class ReceptionMail
{
    static String Hote = "u4.tech.hepl.local";
    static String charset = "iso8859-1";
    public ReceptionMail()
    {

    }

    public static void main(String[] args)
    {
        Properties props = System.getProperties();
        System.out.println("Création d'une session mail");
        props.put("mail.pop3.host", Hote);
        props.put("mail.disable.top", "true");
        props.put("file.encoding", charset);
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

            for (int i = 1; i < messages.length; i++)
            {
                if(messages[i].isMimeType("text/plain"))
                {
                    System.out.println("Message n° " + messages[i].getMessageNumber());
                    System.out.println("Expediteur : " +messages[i].getFrom()[0]);
                    System.out.println("Sujet : " + messages[i].getSubject());
                    System.out.println("Date : " + messages[i].getSentDate());
                    System.out.println("Texte : "+ (String)messages[i].getContent());
                    System.out.println("-----------------------------------");
                }

            }

            // Fermeture de la boîte de réception et du store
            folder.close(false);
            store.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de la réception du mail : " + e);
        }
    }
}
