package controller;

import model.Etudiant;
import database.GestionEtudiants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantController {

    // Ajouter un étudiant
    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getIdEtudiant() == null) {
            System.err.println("Erreur : L'étudiant ou son ID ne peut pas être null.");
            return;
        }

        String query = "INSERT INTO etudiant (id_etudiant, nom, prenom, date_naissance) VALUES (?, ?, ?, ?)";
        try (Connection conn = GestionEtudiants.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, etudiant.getIdEtudiant());
            stmt.setString(2, etudiant.getNom());
            stmt.setString(3, etudiant.getPrenom());
            stmt.setDate(4, etudiant.getDateNaissance());

            stmt.executeUpdate();
            System.out.println("Étudiant ajouté avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
        }
    }

    // Modifier un étudiant
    public void modifierEtudiant(String id, Etudiant nouvelEtudiant) {
        if (nouvelEtudiant == null || id == null || id.isEmpty()) {
            System.err.println("Erreur : L'ID ou l'étudiant à modifier ne peut pas être null.");
            return;
        }

        String query = "UPDATE etudiant SET nom = ?, prenom = ?, date_naissance = ? WHERE id_etudiant = ?";
        try (Connection conn = GestionEtudiants.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nouvelEtudiant.getNom());
            stmt.setString(2, nouvelEtudiant.getPrenom());
            stmt.setDate(3, nouvelEtudiant.getDateNaissance());
            stmt.setString(4, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Étudiant modifié avec succès.");
            } else {
                System.err.println("Aucun étudiant trouvé avec l'ID : " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de l'étudiant : " + e.getMessage());
        }
    }

    // Supprimer un étudiant
    public void supprimerEtudiant(String id) {
        if (id == null || id.isEmpty()) {
            System.err.println("Erreur : L'ID de l'étudiant ne peut pas être null.");
            return;
        }

        String query = "DELETE FROM etudiant WHERE id_etudiant = ?";
        try (Connection conn = GestionEtudiants.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Étudiant supprimé avec succès.");
            } else {
                System.err.println("Aucun étudiant trouvé avec l'ID : " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'étudiant : " + e.getMessage());
        }
    }

    // Récupérer tous les étudiants
    public List<Etudiant> getListeEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiant";

        try (Connection conn = GestionEtudiants.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getString("id_etudiant"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("date_naissance")
                );
                etudiants.add(etudiant);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des étudiants : " + e.getMessage());
        }
        return etudiants;
    }

    // Récupérer un étudiant par ID
    public Etudiant getEtudiantById(String id) {
        if (id == null || id.isEmpty()) {
            System.err.println("Erreur : L'ID de l'étudiant ne peut pas être null.");
            return null;
        }

        String query = "SELECT * FROM etudiant WHERE id_etudiant = ?";
        try (Connection conn = GestionEtudiants.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Etudiant(
                            rs.getString("id_etudiant"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getDate("date_naissance")
                    );
                } else {
                    System.err.println("Aucun étudiant trouvé avec l'ID : " + id);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'étudiant : " + e.getMessage());
        }
        return null;
    }
}
