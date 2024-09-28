package Vue.InterfacesGraphiques;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MailWindow extends JFrame
{
    private JButton Joindre;
    private JButton Envoyer;
    private JTextArea messageArea;
    private JTable attachmentTable;
    private DefaultTableModel tableModel;
    private JTextField destinataireField;
    private JTextField sujetField;

    public MailWindow()
    {
        super("Fenêtre de Pièces Jointes");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Panel supérieur avec les boutons "Joindre" et "Envoyer"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        Joindre = new JButton("Joindre");
        Envoyer = new JButton("Envoyer");
        buttonPanel.add(Joindre);
        buttonPanel.add(Envoyer);
        this.add(buttonPanel, BorderLayout.NORTH);

        // Panel pour "Destinataire" et "Sujet"
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 2, 2));  // 2 lignes, 2 colonnes, espacement de 10px

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
        messageArea.setLineWrap(true);  // Active le retour à la ligne automatique
        messageArea.setWrapStyleWord(true);  // Retour à la ligne sur des mots complets
        messageArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Texte lisible
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

        Joindre.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Ouvrir un JFileChooser pour sélectionner un fichier
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(null);  // Affiche le dialogue

                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();  // Récupère le fichier sélectionné
                    String filePath = selectedFile.getAbsolutePath();  // Récupère le chemin absolu du fichier

                    // Ajouter le chemin du fichier comme nouvelle ligne dans la JTable
                    tableModel.addRow(new Object[]{filePath});
                }
            }
        });

        setVisible(true);
    }

    // Getter pour récupérer les valeurs
    public JButton getJoindreButton() {
        return Joindre;
    }

    public JButton getEnvoyerButton() {
        return Envoyer;
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public String getDestinataire() {
        return destinataireField.getText();
    }

    public String getSujet() {
        return sujetField.getText();
    }


}
