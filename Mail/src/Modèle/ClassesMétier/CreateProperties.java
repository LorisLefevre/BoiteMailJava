package Modèle.ClassesMétier;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateProperties
{

    public static void main(String[] args)
    {
        Properties properties = new Properties();

        // Ajouter des entrées dans le fichier properties
        properties.setProperty("lefevrelo@tech.hepl.local", "azerty1234");

        // Spécifier le chemin du fichier properties
        String filePath = "users.properties";

        // Enregistrer les propriétés dans le fichier
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath))
        {
            properties.store(fileOutputStream, "Liste des utilisateurs");
            System.out.println("Fichier properties créé avec succès !");
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la création du fichier properties : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
