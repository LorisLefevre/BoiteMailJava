package Vue.InterfacesGraphiques;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import Contrôleur.ActionsContrôleur;
import Contrôleur.Contrôleur;
import Modèle.ClassesMétier.Utilisateur;
import Vue.VueMailBoxWindow;

public class MailBoxWindow extends JFrame implements VueMailBoxWindow
{
    private JTextField UserField;
    public JTextField getUser()
    {
        return UserField;
    }

    public void setUser(String User)
    {
        UserField.setText(User);
    }

    private JButton Creer;
    public JButton getCreer()
    {
        return Creer;
    }
    private JButton Raffraichir;
    public JButton getRaffraichir()
    {
        return Raffraichir;
    }

    private JButton Lire;
    public JButton getLire()
    {
        return Lire;
    }

    private JButton Ouvrir;
    public JButton getOuvrir()
    {
        return Ouvrir;
    }

    private JPanel TopPanel;

    private JScrollPane ScrollPane;

    private JTable MailTable;

    private static MailBoxWindow instance;

    public static MailBoxWindow getMailBoxWindow()
    {
        if (instance == null)
        {
            instance = new MailBoxWindow();
        }
        return instance;
    }

    private MailWindow mailWindow;

    private Contrôleur contrôleur;

    private LoginWindow loginWindow;
    public MailBoxWindow()
    {
        super("Boite mail");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        TopPanel = new JPanel();
        TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        UserField = new JTextField("Utilisateur");
        Creer = new JButton("Créer");
        Raffraichir = new JButton("Raffraichir");
        Lire = new JButton("Lire");
        Ouvrir = new JButton("Ouvrir");

        Creer.setActionCommand(ActionsContrôleur.CREER);
        Raffraichir.setActionCommand(ActionsContrôleur.RAFFRAICHIR);
        Lire.setActionCommand(ActionsContrôleur.LIRE);
        Ouvrir.setActionCommand(ActionsContrôleur.OUVRIR);

        TopPanel.add(UserField);
        TopPanel.add(Creer);
        TopPanel.add(Raffraichir);
        TopPanel.add(Lire);
        TopPanel.add(Ouvrir);

        this.add(TopPanel, BorderLayout.NORTH);

        String[] columnNames = {"Expéditeur", "Destinataire", "Date d'envoi", "Sujet", "Pièces Jointes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        MailTable = new JTable(tableModel);
        MailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ScrollPane = new JScrollPane(MailTable);
        this.add(ScrollPane, BorderLayout.CENTER);

        RecupererBoiteMail();
    }

    public void addCreerListener(ActionListener listener)
    {
        Creer.addActionListener(listener);
    }

    public void addRaffraichirListener(ActionListener listener)
    {
        Raffraichir.addActionListener(listener);
    }

    public void addLireListener(ActionListener listener)
    {
        Lire.addActionListener(listener);
    }

    public void addOuvrirListener(ActionListener listener)
    {
        Ouvrir.addActionListener(listener);
    }

    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void run()
    {
        this.setVisible(true);
    }

    @Override
    public void setContrôleurMailBoxWindow(Contrôleur contrôleurMailBoxWindow)
    {
        Creer.setActionCommand(ActionsContrôleur.CREER);
        Raffraichir.setActionCommand(ActionsContrôleur.RAFFRAICHIR);
        Ouvrir.setActionCommand(ActionsContrôleur.OUVRIR);
        Lire.setActionCommand(ActionsContrôleur.LIRE);

        Creer.addActionListener(contrôleurMailBoxWindow);
        Raffraichir.addActionListener(contrôleurMailBoxWindow);
        Ouvrir.addActionListener(contrôleurMailBoxWindow);
        Lire.addActionListener(contrôleurMailBoxWindow);
    }

    public void Creer()
    {
        mailWindow = mailWindow.getMailWindow();
        mailWindow.setExpediteur(UserField.getText());
        mailWindow.getExpediteurField().setEditable(false);
        mailWindow.getDestinataireField().setEditable(true);
        mailWindow.getSujetField().setEditable(true);
        mailWindow.getMessageArea().setEditable(true);
        mailWindow.getJoindreButton().setVisible(true);
        mailWindow.getEnvoyerButton().setVisible(true);
        mailWindow.getAnnulerButton().setVisible(true);
        mailWindow.getFermerButton().setVisible(false);
        mailWindow.setContrôleurMailWindow(contrôleur);
        mailWindow.setVisible(true);
    }

    public void Lire()
    {
        mailWindow = MailWindow.getMailWindow();
        mailWindow.getJoindreButton().setVisible(false);
        mailWindow.getEnvoyerButton().setVisible(false);
        mailWindow.getAnnulerButton().setVisible(false);
        mailWindow.getFermerButton().setVisible(true);
        mailWindow.setContrôleurMailWindow(contrôleur);
        mailWindow.setExpediteur("ABC@domaine.com");
        mailWindow.getExpediteurField().setEditable(false);
        mailWindow.setDestinataire(UserField.getText());
        mailWindow.getDestinataireField().setEditable(false);
        mailWindow.setSujet("Sujet du mail");
        mailWindow.getSujetField().setEditable(false);
        mailWindow.setMessageArea("Bonjour, ceci est un test de lecture de mail en dur");
        mailWindow.getMessageArea().setEditable(false);
        mailWindow.setVisible(true);
    }
    public void RecupererBoiteMail()
    {

    }
}
