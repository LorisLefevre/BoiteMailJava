package Modèle.CoucheAccèsDonnées;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

public class CoucheAccèsDonnéesDAO implements CoucheAccèsDonnées
{

    private static CoucheAccèsDonnéesDAO instance;

    public static CoucheAccèsDonnéesDAO getInstance()
    {
        if (instance == null)
        {
            instance = new CoucheAccèsDonnéesDAO();
        }
        return instance;
    }

    @Override
    public void EnvoyerMailGmail(String Expediteur, String Destinataire, String Sujet, String Message)
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
                return new PasswordAuthentication(Expediteur, "puqv gfzz qdhb yomp");
            }
        });

        try
        {
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

    @Override
    public void EnvoyerMailGmailPieceJointe(String Expediteur, String Destinataire, String Sujet, String Message, List<String> PiecesJointes)
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
                return new PasswordAuthentication(Expediteur, "puqv gfzz qdhb yomp");
            }
        });

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Expediteur));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Destinataire));
            message.setSubject(Sujet);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(Message);
            multipart.addBodyPart(bodyPart);

            for(String PieceJointe : PiecesJointes)
            {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(new DataHandler(new FileDataSource(PieceJointe)));
                attachmentPart.setFileName(PieceJointe);
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            System.out.println("Envoi du mail...");

            javax.mail.Transport.send(message);

            System.out.println("Envoi reussi !");
        }

        catch (Exception e)
        {
            System.out.println("Erreur lors de l'envoi du mail : " + e);
        }
    }

    @Override
    public void EnvoyerMail(String Hote,String Expediteur, String Destinataire, String Sujet, String Message)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", Hote);
        Session session = Session.getDefaultInstance(props, null);

        try
        {
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
    @Override
    public void EnvoyerMailPieceJointe(String Hote, String Expediteur, String Destinataire, String Sujet, String Message, List<String> PiecesJointes)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", Hote);
        Session session = Session.getDefaultInstance(props, null);

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Expediteur));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Destinataire));
            message.setSubject(Sujet);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(Message);
            multipart.addBodyPart(bodyPart);

            for(String PieceJointe : PiecesJointes)
            {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(new DataHandler(new FileDataSource(PieceJointe)));
                attachmentPart.setFileName(PieceJointe);
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            System.out.println("Envoi du mail...");

            javax.mail.Transport.send(message);

            System.out.println("Envoi reussi !");
        }

        catch (Exception e)
        {
            System.out.println("Erreur lors de l'envoi du mail : " + e);
        }
    }

    public Message[] RecevoirMailGmail(String User, String Destinataire)
    {
        getInstance();

        String charset = "iso8859-1";
        String Hote = "pop.gmail.com";

        Properties props = new Properties();
        System.out.println("Création d'une session mail");

        props.put("mail.pop3.host", Hote);
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.ssl.enable", "true");
        props.put("mail.pop3.ssl.trust", "pop.gmail.com");
        props.put("file.encoding", charset);

        String Password = "puqv gfzz qdhb yomp";

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(User, Password);
            }
        });

        Store store = null;
        Folder folder = null;
        try
        {
            store = session.getStore("pop3s");
            store.connect(Hote, User, Password);

            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();
            System.out.println("Nombre de messages : " + folder.getMessageCount());
            System.out.println("Nombre de nouveaux messages : " + folder.getNewMessageCount());

            for (Message message : messages)
            {
                System.out.println("Expéditeur : " + message.getFrom()[0].toString());
                System.out.println("Sujet : " + message.getSubject());
                System.out.println("Date : " + message.getSentDate());
                System.out.println("-----------------------------------");
            }
            //folder.close(false);
            //store.close();
            return messages;
        }

        catch(Exception e)
        {
            System.out.println("Erreur lors de la réception du mail " +e);
        }

        return new Message[0];
    }

}


