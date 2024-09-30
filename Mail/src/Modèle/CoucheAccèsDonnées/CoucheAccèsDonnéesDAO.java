package Modèle.CoucheAccèsDonnées;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CoucheAccèsDonnéesDAO implements CoucheAccèsDonnées
{
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
}


