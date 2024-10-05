package EnvoiMail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;

public class EnvoiMailPieceAttacheeGmail
{
    static String Hote = "gmail.com";
    static String charset = "iso8859-1";

    public EnvoiMailPieceAttacheeGmail()
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
        props.put("file.encoding", charset);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("lorislfvlefevre@gmail.com", "puqv gfzz qdhb yomp"); // Update with actual credentials
            }
        });

        try
        {
            String Expediteur = "lorislfvlefevre@gmail.com";
            String Destinataire = "lorislfvlefevre@gmail.com";
            String Sujet = "Test Mail Java";
            String MessageText = "Bonjour, ceci est un test d'envoi de mail avec Java.";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Expediteur));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Destinataire));
            message.setSubject(Sujet);

            Multipart multipart = new MimeMultipart();

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(MessageText);
            multipart.addBodyPart(bodyPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            String filePath = "C:\\Users\\Loris_76t6f75\\Downloads\\Test.txt";
            FileDataSource source = new FileDataSource(filePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(source.getName());
            multipart.addBodyPart(attachmentPart);

            MimeBodyPart attachmentPart2 = new MimeBodyPart();
            String filePath2 = "C:\\Users\\Loris\\GameImage\\PhotoGT7.jpg";
            FileDataSource source2 = new FileDataSource(filePath2);
            attachmentPart2.setDataHandler(new DataHandler(source2));
            attachmentPart2.setFileName(source2.getName());
            multipart.addBodyPart(attachmentPart2);


            message.setContent(multipart);

            System.out.println("Envoi du mail...");
            Transport.send(message);
            System.out.println("Envoi réussi !");
        }
        catch (Exception e)
        {
            System.out.println("Erreur lors de l'envoi du mail : " + e);
        }
    }
}
