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
import javax.sql.DataSource;

public class EnvoiMailPieceAttachee
{
    static String Hote = "u4.tech.hepl.local";
    static String charset = "iso8859-1";

    public EnvoiMailPieceAttachee()
    {

    }

    public static void main(String[] args)
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", Hote);
        props.put("file.encoding", charset);

        Session session = Session.getDefaultInstance(props, null);
        try
        {
            String Expediteur = "lefevrelo@u4.tech.hepl.local";
            String Destinataire = "lefevrelo@u4.tech.hepl.local";
            String Sujet = "Test Mail Java";
            String Message = "Bonjour, ceci est un test d'envoi de mail avec des pièces attachées avec Java.";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Expediteur));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(Destinataire));
            message.setSubject(Sujet);

            System.out.println("Construction du multipart...");
            Multipart multipart = new MimeMultipart();

            System.out.println("1ère composante du multipart...");
            MimeBodyPart mimebodypart = new MimeBodyPart();
            mimebodypart.setText(Message);
            multipart.addBodyPart(mimebodypart);

            System.out.println("2ème composante du multipart...");
            String Piece = "C:\\Users\\Loris\\AB.txt";
            mimebodypart = new MimeBodyPart();
            FileDataSource source = new FileDataSource(Piece);
            mimebodypart.setDataHandler(new DataHandler(new FileDataSource(Piece)));
            mimebodypart.setFileName(Piece);
            multipart.addBodyPart(mimebodypart);

            message.setContent(multipart);

            System.out.println("Envoi du mail...");

            javax.mail.Transport.send(message);

            System.out.println("Envoi réussi");

        }

        catch (Exception e)
        {
            System.out.println("Erreur lors de l'envoi du mail : " + e);
        }
    }
}
