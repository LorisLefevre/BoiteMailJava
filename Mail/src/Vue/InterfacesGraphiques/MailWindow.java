package Vue.InterfacesGraphiques;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Modèle.ClassesMétier.*;
import Modèle.CoucheAccèsDonnées.*;
import Contrôleur.*;
import Vue.*;

public class MailWindow extends JFrame implements VueMailWindow
{

    private CoucheAccèsDonnées coucheAccèsDonnées;

    private JButton Joindre;
    public JButton getJoindreButton()
    {
        return Joindre;
    }
    private JButton Envoyer;
    public JButton getEnvoyerButton()
    {
        return Envoyer;
    }

    private JButton Annuler;
    public JButton getAnnulerButton()
    {
        return Annuler;
    }

    private JButton Fermer;
    public JButton getFermerButton()
    {
        return Fermer;
    }

    private JTextArea messageArea;
    public JTextArea getMessageArea()
    {
        return messageArea;
    }
   public void setMessageArea(String messageArea)
    {
        this.messageArea.setText(messageArea);
    }
    private JTable attachmentTable;
    private DefaultTableModel tableModel;
    public DefaultTableModel getTableModel()
    {
        return tableModel;
    }

    private JTextField expediteurField;

    public JTextField getExpediteurField()
    {
        return expediteurField;
    }

    public void setExpediteur(String expediteur)
    {
        expediteurField.setText(expediteur);
    }

    public JTextField getDestinataireField()
    {
        return destinataireField;
    }


    private JTextField destinataireField;

    public String getDestinataire()
    {
        return destinataireField.getText();
    }
    public void setDestinataire(String destinataire)
    {
        destinataireField.setText(destinataire);
    }
    private JTextField sujetField;

    public String getSujet()
    {
        return sujetField.getText();
    }

    public JTextField getSujetField()
    {
        return sujetField;
    }

    public void setSujet(String sujet)
    {
        sujetField.setText(sujet);
    }

    private static MailWindow instance;

    public static MailWindow getMailWindow()
    {
        if (instance == null)
        {
            instance = new MailWindow();
        }
        return instance;
    }

    public MailWindow()
    {
        super("Fenêtre d'envoi de mail");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Panel supérieur avec les boutons "Joindre" et "Envoyer"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Joindre = new JButton("Joindre");
        Envoyer = new JButton("Envoyer");
        Annuler = new JButton("Annuler");
        Fermer = new JButton("Fermer");
        buttonPanel.add(Joindre);
        buttonPanel.add(Envoyer);
        buttonPanel.add(Annuler);
        buttonPanel.add(Fermer);
        this.add(buttonPanel, BorderLayout.NORTH);

        // Panel pour "Destinataire" et "Sujet"
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 2, 2));  // 2 lignes, 2 colonnes, espacement de 10px

        JLabel expediteurLabel = new JLabel("Expéditeur :");
        expediteurField = new JTextField();
        inputPanel.add(expediteurLabel);
        inputPanel.add(expediteurField);
        getExpediteurField().setEditable(false);

        JLabel destinataireLabel = new JLabel("Destinataire :");
        destinataireField = new JTextField();
        inputPanel.add(destinataireLabel);
        inputPanel.add(destinataireField);

        JLabel sujetLabel = new JLabel("Sujet :");
        sujetField = new JTextField();
        inputPanel.add(sujetLabel);
        inputPanel.add(sujetField);

        this.add(inputPanel, BorderLayout.NORTH);

        // Crée un panel contenant les boutons et le inputPanel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(buttonPanel, BorderLayout.NORTH);
        topPanel.add(inputPanel, BorderLayout.SOUTH);

        this.add(topPanel, BorderLayout.NORTH);

        // Zone de texte pour le message
        messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        this.add(messageScrollPane, BorderLayout.CENTER);

        // Panel en bas pour la JTable
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        String[] columnNames = {"Chemin de Pièces Jointes"};
        tableModel = new DefaultTableModel(columnNames, 0);
        attachmentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(attachmentTable);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);

        coucheAccèsDonnées = new CoucheAccèsDonnéesDAO();
    }
    public void JoindreListener(ActionListener listener)
    {
        Joindre.addActionListener(listener);
    }

    public void EnvoyerListener(ActionListener listener)
    {
        Envoyer.addActionListener(listener);
    }
    public void AnnulerListener(ActionListener listener)
    {
        Annuler.addActionListener(listener);
    }

    public void FermerListener(ActionListener listener)
    {
        Fermer.addActionListener(listener);
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
    public void setContrôleurMailWindow(Contrôleur contrôleurMailWindow)
    {
        Joindre.setActionCommand(ActionsContrôleur.JOINDRE);
        Envoyer.setActionCommand(ActionsContrôleur.ENVOYER);
        Annuler.setActionCommand(ActionsContrôleur.ANNULER);
        Fermer.setActionCommand(ActionsContrôleur.FERMER);

        Joindre.addActionListener(contrôleurMailWindow);
        Envoyer.addActionListener(contrôleurMailWindow);
        Annuler.addActionListener(contrôleurMailWindow);
        Fermer.addActionListener(contrôleurMailWindow);
    }

    public void Envoyer()
    {
        System.out.println("Envoi d'un nouveau mail");
        String Expediteur = expediteurField.getText();
        String Destinataire = destinataireField.getText();
        String Sujet = sujetField.getText();
        String Message = messageArea.getText();

        System.out.println(Expediteur);
        System.out.println(Destinataire);
        System.out.println(Sujet);
        System.out.println(Message);

        List<String> PiecesJointes = new ArrayList<>();

        try
        {
            int Compteur = attachmentTable.getRowCount();
            String Domaine;
            if(Expediteur.contains("gmail.com"))
            {
                if(Compteur == 0)
                {
                    coucheAccèsDonnées.EnvoyerMailGmail(Expediteur, Destinataire, Sujet, Message);
                }

                else if(Compteur > 0)
                {

                    for(int i = 0; i != Compteur; i++)
                    {
                        String path = attachmentTable.getValueAt(i, 0).toString();
                        PiecesJointes.add(path);

                        System.out.println(path);
                    }

                    coucheAccèsDonnées.EnvoyerMailGmailPieceJointe(Expediteur, Destinataire, Sujet, Message, PiecesJointes);
                }

            }

            else if(Expediteur.contains("u4.tech.hepl.local"))
            {
                Domaine = "u4.tech.hepl.local";
                if(Compteur == 0)
                {
                    coucheAccèsDonnées.EnvoyerMail(Domaine, Expediteur, Destinataire, Sujet, Message);
                }

                else if(Compteur > 0)
                {

                    for(int i = 0; i != Compteur; i++)
                    {
                        String path = attachmentTable.getValueAt(i, 0).toString();
                        PiecesJointes.add(path);

                        System.out.println(path);
                    }

                    coucheAccèsDonnées.EnvoyerMailPieceJointe(Domaine, Expediteur, Destinataire, Sujet, Message, PiecesJointes);
                }
            }

            else if(Expediteur.contains("student.hepl.be"))
            {
                Domaine = "student.hepl.be";
                if(Compteur == 0)
                {
                    coucheAccèsDonnées.EnvoyerMail(Domaine, Expediteur, Destinataire, Sujet, Message);
                }

                else if(Compteur > 0)
                {

                    for(int i = 0; i != Compteur; i++)
                    {
                        String path = attachmentTable.getValueAt(i, 0).toString();
                        PiecesJointes.add(path);

                        System.out.println(path);
                    }

                    coucheAccèsDonnées.EnvoyerMailPieceJointe(Domaine, Expediteur, Destinataire, Sujet, Message, PiecesJointes);
                }
            }

            else if(Expediteur.contains("outlook"))
            {
                Domaine = "outlook";
                if(Compteur == 0)
                {
                    coucheAccèsDonnées.EnvoyerMail(Domaine, Expediteur, Destinataire, Sujet, Message);
                }

                else if(Compteur > 0)
                {

                    for(int i = 0; i != Compteur; i++)
                    {
                        String path = attachmentTable.getValueAt(i, 0).toString();
                        PiecesJointes.add(path);

                        System.out.println(path);
                    }

                    coucheAccèsDonnées.EnvoyerMailPieceJointe(Domaine, Expediteur, Destinataire, Sujet, Message, PiecesJointes);
                }
            }

            showMessage("Message envoyé avec succès");


        }

        catch (Exception e)
        {
            showMessage("Erreur lors de l'envoi du message " + e);
            e.printStackTrace();
        }

        destinataireField.setText("");
        sujetField.setText("");
        messageArea.setText("");
        PiecesJointes.clear();
        DefaultTableModel model = (DefaultTableModel) attachmentTable.getModel();
        model.setRowCount(0);
        this.setVisible(false);
    }

    public void Joindre()
    {
        System.out.println("Joindre une pièce jointe au mail");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();


            tableModel.addRow(new Object[]{filePath});
        }
    }

    public void Annuler()
    {
        destinataireField.setText("");
        sujetField.setText("");
        messageArea.setText("");
        DefaultTableModel model = (DefaultTableModel) attachmentTable.getModel();
        model.setRowCount(0);
        this.setVisible(false);
    }

    public void Fermer()
    {
        destinataireField.setText("");
        sujetField.setText("");
        messageArea.setText("");
        DefaultTableModel model = (DefaultTableModel) attachmentTable.getModel();
        model.setRowCount(0);
        this.setVisible(false);
    }
}
