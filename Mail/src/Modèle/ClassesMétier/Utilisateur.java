package Modèle.ClassesMétier;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.*;
public class Utilisateur extends Personne
{
    private static String Username;
    private static String Password;
    private static HashMap<String, String> users = new HashMap<>();

    public Utilisateur()
    {
        super();
        Username = "lefevrelo@u4.tech.hepl.local";
        Password = "azerty1234";
    }

    public Utilisateur(String Username, String Password)
    {
        this.Username = Username;
        this.Password = Password;
    }

    public Utilisateur(String Nom, String Prenom, String Username, String Password)
    {
        super(Nom, Prenom);
        this.Username = Username;
        this.Password = Password;
    }


    public boolean verifieDomaine(String domaine)
    {
        return Username != null && Username.toLowerCase().contains(domaine.toLowerCase());
    }

    public  static void DomaineHEPL()
    {

    }

    public static void DomaineGmail()
    {
        MailGmail mailGmail = new MailGmail(Username, Password);


        mailGmail.configurerSessionGmail();


        Session session = mailGmail.getSession();

        System.out.println("Session Gmail prête à l'emploi pour l'utilisateur : " + Username);
    }
    public  static Utilisateur seConnecter(String username, String password)
    {
        loadUserData();
        if (users.containsKey(username))
        {
            String storedPassword = users.get(username);

            if (storedPassword.equals(password))
            {
                System.out.println("Connexion réussie pour " + username);
                Utilisateur utilisateur = new Utilisateur(username, password);
                if(utilisateur.verifieDomaine("u4.tech.hepl.local"))
                {
                   DomaineHEPL();
                }

                else if(utilisateur.verifieDomaine("gmail.com"))
                {
                    DomaineGmail();
                }

                return utilisateur;
            }
            else
            {
                System.out.println("Mot de passe incorrect pour " + username);
                return null;
            }


        }
        else
        {
            // Créer un nouveau administrateur
            users.put(username, password);
            saveUserData(); // Sauvegarder les données des administrateurs dans le fichier
            System.out.println("Nouvel utilisateur créé : " + username);
            return new Utilisateur(username, password);
        }
    }

    private static void loadUserData()
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("users.properties"))
        {
            properties.load(fis);
            for (String username : properties.stringPropertyNames())
            {
                users.put(username, properties.getProperty(username));
            }
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void saveUserData()
    {
        Properties properties = new Properties();
        for (String username : users.keySet()) {
            properties.setProperty(username, users.get(username));
        }
        try (FileOutputStream fos = new FileOutputStream("users.properties"))
        {
            properties.store(fos, "Utilisateurs enregistrés");
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getters and setters
    public String getUsername()
    {
        return Username;
    }

    public void setUsername(String Username)
    {
        this.Username = Username;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setAdminPassword(String Password)
    {
        this.Password = Password;
    }


    @Override
    public void Afficher()
    {
        super.Afficher();
        System.out.println("Username : " + Username);
        System.out.println("Password : " + Password);
    }


    @Override
    public void Saisir()
    {
        Scanner scanner = new Scanner(System.in);
        super.Saisir();

        System.out.println("Entrez le nom d'utilisateur : ");
        this.Username = scanner.nextLine();

        System.out.println("Entrez le mot de passe : ");
        this.Password = scanner.nextLine();
    }

    // Surcharge de la méthode toString
    @Override
    public String toString()
    {
        return super.toString() +
                "\nUsername : " + Username +
                "\nPassword : " + Password;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        if (!super.equals(o)) return false;
        Utilisateur that = (Utilisateur) o;
        return Username.equals(that.Username) && Password.equals(that.Password);
    }
}