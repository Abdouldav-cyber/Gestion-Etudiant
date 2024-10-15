package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controller.EtudiantController; // Votre contrôleur pour les étudiants
import model.Etudiant; // Votre classe modèle pour les étudiants
import controller.ResultatController; // Votre contrôleur pour les résultats

public class MainView extends JFrame {
    private JTable table;
    private EtudiantController etudiantController;

    public MainView(EtudiantController controller) {
        this.etudiantController = controller;
        initialize();
    }

    private void initialize() {
        setTitle("Gestion des Étudiants et Cours");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Ajouter un panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton btnAjouter = new JButton("Ajouter Étudiant");
        btnAjouter.addActionListener(e -> ajouterEtudiant());

        JButton btnModifier = new JButton("Modifier Étudiant");
        btnModifier.addActionListener(e -> modifierEtudiant());

        JButton btnSupprimer = new JButton("Supprimer Étudiant");
        btnSupprimer.addActionListener(e -> supprimerEtudiant());

        JButton coursButton = new JButton("Gérer Cours"); // Créez le bouton pour les cours
        coursButton.addActionListener(e -> {
            CoursView coursView = new CoursView(); // Créez une instance de CoursView
            coursView.setVisible(true); // Affichez la vue des cours
        });

        JButton resultatButton = new JButton("Gérer les Résultats"); // Bouton pour gérer les résultats
        resultatButton.addActionListener(e -> {
            ResultatView resultatView = new ResultatView(new ResultatController());
            resultatView.setVisible(true); // Affichez la vue des résultats
        });

        // Ajouter les boutons au panneau
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(coursButton); // Ajoutez le bouton de gestion des cours
        buttonPanel.add(resultatButton); // Ajoutez le bouton pour gérer les résultats

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Initialisation de la table
        table = new JTable();
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
        rafraichirTable(); // Rafraîchir la table au démarrage
    }

    private void ajouterEtudiant() {
        // Saisie des informations de l'étudiant
        String id = JOptionPane.showInputDialog("ID de l'étudiant:");
        String nom = JOptionPane.showInputDialog("Nom de l'étudiant:");
        String prenom = JOptionPane.showInputDialog("Prénom de l'étudiant:");
        String dateNaissance = JOptionPane.showInputDialog("Date de naissance (yyyy-MM-dd):");

        if (id != null && nom != null && prenom != null && dateNaissance != null &&
                !id.trim().isEmpty() && !nom.trim().isEmpty() && !prenom.trim().isEmpty() && !dateNaissance.trim().isEmpty()) {
            try {
                // Ajouter l'étudiant au contrôleur et mettre à jour la table
                Etudiant etudiant = new Etudiant(id, nom, prenom, java.sql.Date.valueOf(dateNaissance));
                etudiantController.ajouterEtudiant(etudiant);
                rafraichirTable(); // Mettre à jour la table pour afficher les nouvelles données
                JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès.");
            } catch (IllegalArgumentException e) {
                // Gestion des erreurs lors de la conversion de la date
                JOptionPane.showMessageDialog(this, "Erreur : Date de naissance invalide. Utilisez le format yyyy-MM-dd.");
            }
        } else {
            // Message d'erreur si les champs ne sont pas remplis correctement
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
        }
    }

    private void modifierEtudiant() {
        String id = JOptionPane.showInputDialog("Entrez l'ID de l'étudiant à modifier:");

        // Récupérer l'étudiant existant à partir de l'ID
        Etudiant etudiantExist = etudiantController.getEtudiantById(id);

        if (etudiantExist != null) {
            // Demander les nouvelles informations de l'étudiant
            String nouveauNom = JOptionPane.showInputDialog("Nouveau nom de l'étudiant:", etudiantExist.getNom());
            String nouveauPrenom = JOptionPane.showInputDialog("Nouveau prénom de l'étudiant:", etudiantExist.getPrenom());
            String dateNaissance = JOptionPane.showInputDialog("Nouvelle date de naissance (yyyy-MM-dd):", etudiantExist.getDateNaissance().toString());

            // Création du nouvel étudiant avec les nouvelles informations
            Etudiant nouvelEtudiant = new Etudiant(id, nouveauNom, nouveauPrenom, java.sql.Date.valueOf(dateNaissance));

            // Appel de la méthode modifier de EtudiantController
            etudiantController.modifierEtudiant(id, nouvelEtudiant); // Vous devez avoir cette méthode définie dans votre controller
            rafraichirTable(); // Mettre à jour la table après modification
            JOptionPane.showMessageDialog(this, "Étudiant modifié avec succès.");
        } else {
            JOptionPane.showMessageDialog(this, "Aucun étudiant trouvé avec cet ID.");
        }
    }

    private void supprimerEtudiant() {
        // Demander l'ID de l'étudiant à supprimer
        String id = JOptionPane.showInputDialog("ID de l'étudiant à supprimer:");
        if (id != null && !id.trim().isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer l'étudiant avec l'ID: " + id + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                etudiantController.supprimerEtudiant(id);
                rafraichirTable(); // Mettre à jour la table
                JOptionPane.showMessageDialog(this, "Étudiant supprimé avec succès.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.");
        }
    }

    // Méthode pour rafraîchir la table avec les données mises à jour
    private void rafraichirTable() {
        // Récupérer la liste des étudiants et mettre à jour le modèle de la table
        List<Etudiant> etudiants = etudiantController.getListeEtudiants();

        // Assurez-vous d'utiliser DefaultTableModel pour mettre à jour le modèle de la table
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Date de Naissance"}, 0);

        for (Etudiant e : etudiants) {
            model.addRow(new Object[]{e.getIdEtudiant(), e.getNom(), e.getPrenom(), e.getDateNaissance()});
        }

        table.setModel(model); // Définir le modèle de la table avec les nouvelles données
    }

    public static void main(String[] args) {
        EtudiantController controller = new EtudiantController(); // Assurez-vous que votre contrôleur est initialisé ici
        MainView mainView = new MainView(controller);
        mainView.setVisible(true);
    }
}