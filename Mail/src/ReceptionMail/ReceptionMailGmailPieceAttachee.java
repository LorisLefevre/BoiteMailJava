package ReceptionMail;

import javax.mail.*;
import java.util.Properties;
public class ReceptionMailGmailPieceAttachee
{
    static String charset = "iso8859-1";
    static String Hote = "pop.gmail.com";  // Serveur POP3 de Gmail

    public ReceptionMailGmailPieceAttachee()
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

            for (int i = 0; i < messages.length; i++)
            {

                if(messages[i].isMimeType("multipart/*"))
                {
                    System.out.println("------------------MULTI PART----------------------------");
                }

                else
                {
                    System.out.println("------------------SIMPLE PART----------------------------");
                    System.out.println("Message n° " + messages[i].getMessageNumber());
                    System.out.println("Expediteur : " +messages[i].getFrom()[0]);
                    System.out.println("Sujet : " + messages[i].getSubject());
                    System.out.println("Date : " + messages[i].getSentDate());
                    System.out.println("Texte : "+ (String)messages[i].getContent());
                    System.out.println("-----------------------------------");
                }
            }

            folder.close(false);
            store.close();
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de la réception du mail : " + e);
        }
    }
}
