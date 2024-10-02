package ReceptionMail;

import javax.mail.*;
import java.util.Properties;

public class ReceptionMailGmail
{
    static String charset = "iso8859-1";
    static String Hote = "pop.gmail.com";  // Serveur POP3 de Gmail

    public ReceptionMailGmail()
    {
    }

    public static void main(String[] args)
    {
        Properties props = new Properties();
        System.out.println("Création d'une session mail");


        props.put("mail.pop3.host", Hote);
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.ssl.enable", "true");
        props.put("mail.pop3.ssl.trust", "pop.gmail.com");
        props.put("file.encoding", charset);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("lorislfvlefevre@gmail.com", "puqv gfzz qdhb yomp");
            }
        });

        try
        {
            Store store = session.getStore("pop3s");
            store.connect(Hote, "lorislfvlefevre@gmail.com", "puqv gfzz qdhb yomp");

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            System.out.println("Nombre de messages : " + folder.getMessageCount());
            System.out.println("Nombre de nouveaux messages : " + folder.getNewMessageCount());

            for (Message message : messages)
            {
                System.out.println("Message n° " + message.getMessageNumber());
                System.out.println("Expéditeur : " + message.getFrom()[0].toString());
                System.out.println("Sujet : " + message.getSubject());
                System.out.println("Date : " + message.getSentDate());
                System.out.println("-----------------------------------");
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
