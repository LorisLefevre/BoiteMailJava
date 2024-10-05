package Vue.InterfacesGraphiques;

import javax.mail.Message;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import Contrôleur.ActionsContrôleur;
import Contrôleur.Contrôleur;
import Modèle.ClassesMétier.Utilisateur;
import Modèle.CoucheAccèsDonnées.CoucheAccèsDonnées;
import Modèle.CoucheAccèsDonnées.CoucheAccèsDonnéesDAO;
import Vue.VueMailBoxWindow;

public class MailBoxWindow extends JFrame implements VueMailBoxWindow
{
    private CoucheAccèsDonnées coucheAccèsDonnées;
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

    public JTable getMailTable()
    {
        return MailTable;
    }

    private DefaultTableModel tableModel;

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

        String[] columnNames = {"Expéditeur", "Destinataire", "Date d'envoi", "Sujet", "Message"};
        tableModel = new DefaultTableModel(columnNames, 0);
        MailTable = new JTable(tableModel);
        MailTable.setModel(tableModel);
        MailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ScrollPane = new JScrollPane(MailTable);
        this.add(ScrollPane, BorderLayout.CENTER);

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

        getMailTable();
        int selectedRow = MailTable.getSelectedRow();

        if (selectedRow != -1)
        {
            String expediteur = MailTable.getValueAt(selectedRow, 0).toString();
            String destinataire = MailTable.getValueAt(selectedRow, 1).toString();
            String sujet = MailTable.getValueAt(selectedRow, 3).toString();
            String message = MailTable.getValueAt(selectedRow, 4).toString();


            mailWindow.getJoindreButton().setVisible(false);
            mailWindow.getEnvoyerButton().setVisible(false);
            mailWindow.getAnnulerButton().setVisible(false);
            mailWindow.getFermerButton().setVisible(true);
            mailWindow.setContrôleurMailWindow(contrôleur);

            mailWindow.setExpediteur(expediteur);
            mailWindow.getExpediteurField().setEditable(false);

            mailWindow.setDestinataire(destinataire);
            mailWindow.getDestinataireField().setEditable(false);

            mailWindow.setSujet(sujet);
            mailWindow.getSujetField().setEditable(false);


            mailWindow.setMessageArea(message);
            mailWindow.getMessageArea().setEditable(false);

            mailWindow.setVisible(true);
        }
        else
        {
            System.out.println("Aucun mail sélectionné.");
        }
    }

    public void RecupererBoiteMail()
    {
        System.out.println("\nRécupération de la boite mail");
        String User = UserField.getText();
        String Expediteur;
        String Destinataire = User;
        System.out.println(Destinataire);
        String Sujet;
        String Date;
        String Message;

        SimpleDateFormat FormatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try
        {
            System.out.println("1");
            if(Destinataire.contains("gmail.com"))
            {
                System.out.println("2");
                this.coucheAccèsDonnées = CoucheAccèsDonnéesDAO.getInstance();
                Message[] messages = coucheAccèsDonnées.RecevoirMailGmail(User, Destinataire);
                System.out.println("Messages " + messages.length);

                for(Message message : messages)
                {
                    Expediteur = message.getFrom()[0].toString();
                    Sujet = message.getSubject();
                    Date = FormatDate.format(message.getSentDate());
                    Message = (String)message.getContent();

                    System.out.println("Expéditeur: " + Expediteur);
                    System.out.println("Destinataire: " + Destinataire);
                    System.out.println("Date: " + Date);
                    System.out.println("Sujet: " + Sujet);
                    System.out.println("Message : " + Message);

                    Object[] row = {Expediteur, Destinataire, Date, Sujet, Message};
                    tableModel.addRow(row);

                }
            }
        }

        catch(Exception e)
        {
            showMessage("Erreur lors de la récupération de la boite mail " + e);
            e.printStackTrace();
        }
    }

    public void Raffraichir()
    {
        RecupererBoiteMail();
    }
}
