package Vue.InterfacesGraphiques;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import Contrôleur.ActionsContrôleur;

public class MailBoxWindow extends JFrame
{
    private JLabel User;
    public JLabel getUser()
    {
        return User;
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

    public MailBoxWindow()
    {
        super("Boite mail");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        TopPanel = new JPanel();
        TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        User = new JLabel("Utilisateur");
        Creer = new JButton("Créer");
        Raffraichir = new JButton("Raffraichir");
        Lire = new JButton("Lire");
        Ouvrir = new JButton("Ouvrir");

        Creer.setActionCommand(ActionsContrôleur.CREER);
        Raffraichir.setActionCommand(ActionsContrôleur.RAFFRAICHIR);
        Lire.setActionCommand(ActionsContrôleur.LIRE);
        Ouvrir.setActionCommand(ActionsContrôleur.OUVRIR);

        TopPanel.add(User);
        TopPanel.add(Creer);
        TopPanel.add(Raffraichir);
        TopPanel.add(Lire);
        TopPanel.add(Ouvrir);

        this.add(TopPanel, BorderLayout.NORTH);

        String[] columnNames = {"Expéditeur", "Destinataire", "Date d'envoi", "Sujet", "Pièces Jointes"};

        // Modèle de la JTable
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        MailTable = new JTable(tableModel);

        // Activer le redimensionnement automatique des colonnes
        MailTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Mettre la JTable dans un JScrollPane pour avoir une barre de défilement si nécessaire
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

    public static void main(String[] args)
    {
        MailBoxWindow mailBoxWindow = new MailBoxWindow();
        mailBoxWindow.setVisible(true);
    }
}
