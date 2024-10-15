package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import controller.ResultatController;
import model.Resultat;
import java.sql.Date;

public class ResultatView extends JFrame {
    private ResultatController resultatController;

    public ResultatView(ResultatController controller) {
        this.resultatController = controller;
        initialize();
    }

    private void initialize() {
        setTitle("Gérer les Résultats");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton ajouterButton = new JButton("Ajouter Résultat");
        ajouterButton.addActionListener(e -> ajouterResultat());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Autres éléments d'interface (tableau, etc.) peuvent être ajoutés ici

        setVisible(true);
    }

    private void ajouterResultat() {
        String id = JOptionPane.showInputDialog("ID du résultat:");
        String idEtudiant = JOptionPane.showInputDialog("ID de l'étudiant:");
        String idCours = JOptionPane.showInputDialog("ID du cours:");
        double note = Double.parseDouble(JOptionPane.showInputDialog("Note:"));
        String dateString = JOptionPane.showInputDialog("Date (yyyy-MM-dd):");

        Date date = java.sql.Date.valueOf(dateString);

        Resultat resultat = new Resultat(id, idEtudiant, idCours, note, date);
        resultatController.ajouterResultat(resultat);

        JOptionPane.showMessageDialog(this, "Résultat ajouté avec succès.");
        // Optionnel : rafraîchir la table ou l'affichage
    }

    // Méthodes pour modifier et supprimer les résultats peuvent être ajoutées ici
}