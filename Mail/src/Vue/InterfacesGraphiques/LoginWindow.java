package Vue.InterfacesGraphiques;

import Contrôleur.*;
import Modèle.ClassesMétier.Utilisateur;
import Vue.VueLoginWindow;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements VueLoginWindow
{
    private LoginWindow loginWindow;

    private MailBoxWindow mailboxwindow;
    private MailWindow mailwindow;

    private Contrôleur contrôleur;

    public Contrôleur getContrôleur()
    {
        return contrôleur;
    }

    private JTextField UsernameField;
    public JTextField getUsernameField()
    {
        //Faire en sorte de récupérer le nom de l'utilisateur (lefevrelo) et le domaine (@u4.tech.hepl.local)
        return UsernameField;
    }

    private JPasswordField PasswordField;
    public JPasswordField getPasswordField()
    {
        return PasswordField;
    }

    private JButton LoginButton;
    public JButton getLoginButton()
    {
        return LoginButton;
    }

    private JPanel MainPanel;

    private static LoginWindow instance;

    public static LoginWindow getLoginWindow()
    {
        if (instance == null)
        {
            instance = new LoginWindow();
        }
        return instance;
    }

    public LoginWindow()
    {
        super("Login...");
        setSize(350,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel = new JPanel();
        UsernameField = new JTextField(20);
        PasswordField = new JPasswordField(20);
        LoginButton = new JButton("Login");

        LoginButton.setActionCommand(ActionsContrôleur.LOGIN);

        MainPanel.add(new JLabel("Username:"));
        MainPanel.add(UsernameField);
        MainPanel.add(new JLabel("Password:"));
        MainPanel.add(PasswordField);
        MainPanel.add(LoginButton);

        setContentPane(MainPanel);

    }

    public String getUsername()
    {
        return UsernameField.getText();
    }

    public String getPassword()
    {
        return new String(PasswordField.getPassword());
    }

    public void addLoginListener(ActionListener listener)
    {
        LoginButton.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void run()
    {
        this.loginWindow = this;
        setVisible(true);
    }

    @Override
    public void setContrôleur(Contrôleur Contrôleur)
    {
        this.loginWindow = this;
        LoginButton.setActionCommand(ActionsContrôleur.LOGIN);
        loginWindow.addLoginListener(Contrôleur);
    }

    @Override
    public Utilisateur Login()
    {
        System.out.println("Connexion de l'utilisateur...");
        String Username = loginWindow.getUsername();
        String Password = loginWindow.getPassword();
        Utilisateur utilisateur = Utilisateur.seConnecter(Username, Password);

        if(utilisateur != null)
        {
            System.out.println("Connexion reussie !");

            if(this.mailboxwindow == null)
            {

                System.out.println("Nouvelle fenêtre d'accueil...");
                this.mailboxwindow = new MailBoxWindow();
            }
            this.mailboxwindow = MailBoxWindow.getMailBoxWindow();
            mailboxwindow.setUser(Username);
            mailboxwindow.getUser().setEditable(false);
            mailboxwindow.setVisible(true);
            mailboxwindow.setContrôleurMailBoxWindow(contrôleur);
            return utilisateur;

            /*if(this.mailwindow == null)
            {
                this.mailwindow = new MailWindow();
            }
            this.mailwindow = mailwindow.getMailWindow();
            mailwindow.setExpediteur(Username);
            mailwindow.setVisible(true);
            mailwindow.setContrôleurMailWindow(contrôleur);
            return utilisateur;*/
        }

        else
        {
            loginWindow.showMessage("Login ou mot de passe incorrect !");
        }

        return null;
    }


}
