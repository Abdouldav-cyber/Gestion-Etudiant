package view;

import controller.CoursController;
import model.Cours;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoursView extends JFrame {
    private CoursController coursController;
    private JTable table;
    private DefaultTableModel model;

    public CoursView() {
        coursController = new CoursController();
        initUI();
    }

    private void initUI() {
        setTitle("Gestion des Cours");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Créer le modèle de table
        model = new DefaultTableModel(new String[]{"ID", "Nom", "Description"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajouter un panel pour les boutons
        JPanel panel = new JPanel();
        JButton ajouterButton = new JButton("Ajouter Cours");
        JButton modifierButton = new JButton("Modifier Cours");
        JButton supprimerButton = new JButton("Supprimer Cours");

        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);

        // Action pour le bouton Ajouter
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("ID du Cours:");
                String nom = JOptionPane.showInputDialog("Nom du Cours:");
                String description = JOptionPane.showInputDialog("Description du Cours:");

                if (id != null && nom != null && description != null) {
                    Cours cours = new Cours(id, nom, description);
                    coursController.ajouterCours(cours);
                    model.addRow(new Object[]{id, nom, description});
                }
            }
        });

        // Action pour le bouton Modifier
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) model.getValueAt(selectedRow, 0);
                    String nom = JOptionPane.showInputDialog("Nouveau Nom du Cours:", model.getValueAt(selectedRow, 1));
                    String description = JOptionPane.showInputDialog("Nouvelle Description du Cours:", model.getValueAt(selectedRow, 2));

                    if (nom != null && description != null) {
                        Cours nouveauCours = new Cours(id, nom, description);
                        coursController.modifierCours(id, nouveauCours);
                        model.setValueAt(nom, selectedRow, 1);
                        model.setValueAt(description, selectedRow, 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un cours à modifier.");
                }
            }
        });

        // Action pour le bouton Supprimer
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String id = (String) model.getValueAt(selectedRow, 0);
                    coursController.supprimerCours(id);
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un cours à supprimer.");
                }
            }
        });

        // Ajout des composants à la fenêtre
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoursView view = new CoursView();
            view.setVisible(true);
        });
    }
}