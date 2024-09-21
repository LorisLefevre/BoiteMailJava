package Contrôleur;

import Modèle.ClassesMétier.Utilisateur;
import Vue.*;
import EnvoiMail.*;
import ReceptionMail.*;
import Vue.InterfacesGraphiques.LoginWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JColorChooser.showDialog;

public class Contrôleur implements ActionListener
{
  /*  private VueLoginWindow vueLoginWindow;
    private LoginWindow loginWindow;

    //private MailBoxWindow mailboxwindow;

    public Contrôleur(CoucheAccèsDonnées model, VueLoginWindow vue)
    {
        this.model = model;
        this.vueLoginWindow = vue;
        this.vueLoginWindow.setContrôleur(this);
    }

    public void ContrôleurMailBox(VueMailBox vueMailBox) {
        this.mailboxwindow = (MailBoxWindow) vueMailBox;
    }

    public void run() {
        this.vueLoginWindow.run();
    }*/

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(ActionsContrôleur.LOGIN))
        {
            /*vueLoginWindow.Login();
            vueMailBox = MailBoxWindows.getMailBoxWindow();
            ContrôleurMailBox(vueMailBox);*/
        }

        if (e.getActionCommand().equals(ActionsContrôleur.CREER))
        {
            System.out.println("Création d'un nouveau mail");
        }

        if (e.getActionCommand().equals(ActionsContrôleur.ENVOYER))
        {
            System.out.println("Envoi d'un nouveau mail");
        }

        if (e.getActionCommand().equals(ActionsContrôleur.JOINDRE))
        {
            System.out.println("Joindre une pièce jointe au mail");
        }

        if (e.getActionCommand().equals(ActionsContrôleur.RAFFRAICHIR))
        {
            System.out.println("Raffraichir la boite mail");
        }

        if (e.getActionCommand().equals(ActionsContrôleur.LIRE))
        {
            System.out.println("Lire le mail");
        }

        if (e.getActionCommand().equals(ActionsContrôleur.OUVRIR))
        {
            System.out.println("Ouvrir le mail");
        }
    }

}
