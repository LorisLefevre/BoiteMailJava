package Modèle.ClassesMétier;

import java.util.Objects;
import java.util.Scanner;
public class Personne
{
    protected String Nom;
    protected String Prenom;

    public void setNom(String nom)
    {
        Nom = nom;
    }

    public String getNom(String nom)
    {
        return nom;
    }

    public void setPrenom(String prenom)
    {
        Prenom = prenom;
    }

    public String getPrenom(String prenom)
    {
        return prenom;
    }

    public Personne()
    {
        Nom = "Default Name";
        Prenom = "Default FirstName";
    }
    public Personne(String Nom, String Prenom)
    {
        this.Nom = Nom;
        this.Prenom = Prenom;
    }

    public void Afficher()
    {
        System.out.println("Nom : " + Nom);
        System.out.println("Prénom : " + Prenom);
    }

    public void Saisir()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom : ");
        this.Nom = scanner.nextLine();

        System.out.println("Entrez le prénom : ");
        this.Prenom = scanner.nextLine();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Personne personne = (Personne) o;
        return Objects.equals(Nom, personne.Nom) && Objects.equals(Prenom, personne.Prenom);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(Nom, Prenom);
    }

    @Override
    public String toString()
    {
        return "Modèle.CoucheAccèsDonnées.ClassesMétier.Personne{" +
                "Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                '}';
    }

    public static void main(String[] args)
    {
        System.out.println("Test de la classe Modèle.CoucheAccèsDonnées.ClassesMétier.Personne");

        System.out.println("1.Test du constructeur par défaut");

        Personne personne = new Personne();
        personne.getNom(personne.Nom);
        personne.setNom(personne.Nom);
        personne.getPrenom(personne.Prenom);
        personne.setPrenom(personne.Prenom);
        personne.Afficher();

        System.out.println("2.Test du constructeur d'initialisation");

        Personne personne1 = new Personne("Lefevre", "Loris");
        personne1.Afficher();

        System.out.println("3.Test des méthodes set et get");

        Personne personne2 = new Personne();
        personne2.setNom("Toto");
        personne2.setPrenom("Pierre");
        personne2.Afficher();

        System.out.println("4.Test de la méthode Saisir");

        Personne personne3 = new Personne();
        personne3.Saisir();
        personne3.Afficher();

        System.out.println("5.Test de la méthode Equals");

        personne.Afficher();
        personne3.Afficher();

        if(personne.equals(personne3))
        {
            System.out.println("Ce sont les mêmes personnes");
        }

        else
        {
            System.out.println("Ce sont des personnes différentes");
        }

        Personne personne4 = new Personne();

        personne4 = personne3;

        personne3.Afficher();
        personne4.Afficher();

        if(personne3.equals(personne4))
        {
            System.out.println("Ce sont les mêmes personnes");
        }

        else
        {
            System.out.println("Ce sont des personnes différentes");
        }

    }

}