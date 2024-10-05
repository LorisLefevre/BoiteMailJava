package Contrôleur;

import Modèle.CoucheAccèsDonnées.CoucheAccèsDonnées;
import Vue.*;
import Vue.InterfacesGraphiques.LoginWindow;
import Vue.InterfacesGraphiques.MailBoxWindow;
import Vue.InterfacesGraphiques.MailWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JColorChooser.showDialog;

public class Contrôleur implements ActionListener
{
    private CoucheAccèsDonnées model;
    private VueLoginWindow vueLoginWindow;
    private LoginWindow loginWindow;

    private MailBoxWindow mailboxwindow;
    private VueMailBoxWindow vueMailBoxWindow;

    private MailWindow mailwindow;
    private VueMailWindow vueMailWindow;

    public Contrôleur(CoucheAccèsDonnées model, VueLoginWindow vue)
    {
        this.model = model;
        this.vueLoginWindow = vue;
        this.vueLoginWindow.setContrôleur(this);
    }

    public void ContrôleurLoginWindow(VueLoginWindow vueLoginWindow)
    {
        this.loginWindow = (LoginWindow) vueLoginWindow;
        vueLoginWindow.setContrôleur(this);
    }
    public void ContrôleurMailBox(VueMailBoxWindow vueMailBox)
    {
        this.mailboxwindow = (MailBoxWindow) vueMailBox;
        vueMailBox.setContrôleurMailBoxWindow(this);
    }

    public void ContrôleurMailWindow(VueMailWindow vueMailWindow)
    {
        this.mailwindow = (MailWindow) vueMailWindow;
        vueMailWindow.setContrôleurMailWindow(this);
    }

    public void run()
    {
        this.vueLoginWindow.run();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(ActionsContrôleur.LOGIN))
        {
            vueLoginWindow.Login();
            vueMailWindow = MailWindow.getMailWindow();
            ContrôleurMailWindow(vueMailWindow);
            vueMailBoxWindow = MailBoxWindow.getMailBoxWindow();
            ContrôleurMailBox(vueMailBoxWindow);
        }

        if (e.getActionCommand().equals(ActionsContrôleur.CREER))
        {
            System.out.println("Création d'un nouveau mail");
            mailboxwindow.Creer();
        }

        if (e.getActionCommand().equals(ActionsContrôleur.ENVOYER))
        {
            System.out.println("Envoi d'un nouveau mail");
            mailwindow.Envoyer();
        }

        if (e.getActionCommand().equals(ActionsContrôleur.JOINDRE))
        {
            System.out.println("Joindre une pièce jointe au mail");
            mailwindow.Joindre();
        }

        if (e.getActionCommand().equals(ActionsContrôleur.RAFFRAICHIR))
        {
            System.out.println("Raffraichir la boite mail");
            mailboxwindow.Raffraichir();
        }

        if (e.getActionCommand().equals(ActionsContrôleur.LIRE))
        {
            System.out.println("Lire le mail");
            vueLoginWindow = LoginWindow.getLoginWindow();
            ContrôleurLoginWindow(vueLoginWindow);
            mailboxwindow.Lire();
        }

        if (e.getActionCommand().equals(ActionsContrôleur.OUVRIR))
        {
            System.out.println("Ouvrir le mail");
        }

        if (e.getActionCommand().equals(ActionsContrôleur.ANNULER))
        {
            System.out.println("Annuler la création de mail");
            mailwindow.Annuler();

        }

        if (e.getActionCommand().equals(ActionsContrôleur.FERMER))
        {
            System.out.println("Fermer le mail");
            mailwindow.Fermer();
        }
    }

}
