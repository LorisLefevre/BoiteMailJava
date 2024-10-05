package Vue;

import Modèle.ClassesMétier.Utilisateur;
import  Contrôleur.*;
import Vue.InterfacesGraphiques.LoginWindow;

public class VueLoginWindowConsole implements VueLoginWindow
{
    private VueLoginWindow vue;

    private LoginWindow loginWindow;

    public VueLoginWindowConsole(LoginWindow loginWindow)
    {
        this.loginWindow = loginWindow;
    }

    @Override
    public void run()
    {
        loginWindow.setVisible(true);
    }

    @Override
    public void setContrôleur(Contrôleur contrôleur)
    {
        loginWindow.addLoginListener(contrôleur);
        contrôleur = contrôleur;
    }

    @Override
    public Utilisateur Login()
    {
        System.out.println("Tentative de connexion de l'utilisateur...");
        String Username = loginWindow.getUsername();
        String Password = loginWindow.getPassword();
        Utilisateur utilisateur = Utilisateur.seConnecter(Username, Password);

        if(utilisateur != null)
        {
            loginWindow.showMessage("Connexion reussie !");
            return utilisateur;
        }

        else
        {
            loginWindow.showMessage("Login ou mot de passe incorrect !");
            return null;
        }
    }
}
