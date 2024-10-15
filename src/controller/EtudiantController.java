package controller;

import model.Etudiant;

import java.util.ArrayList;
import java.util.List;

public class EtudiantController {
    private List<Etudiant> etudiants; // Liste pour stocker les étudiants

    public EtudiantController() {
        this.etudiants = new ArrayList<>();
    }

    // Méthode pour ajouter un étudiant
    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant != null && etudiant.getIdEtudiant() != null && !etudiant.getIdEtudiant().isEmpty()) {
            etudiants.add(etudiant); // Ajoute l'étudiant à la liste
        } else {
            throw new IllegalArgumentException("L'étudiant doit avoir un ID valide.");
        }
    }

    // Méthode pour modifier un étudiant
    public void modifierEtudiant(String id, Etudiant nouvelEtudiant) {
        Etudiant etudiantExist = getEtudiantById(id);
        if (etudiantExist != null) {
            // Remplace les informations de l'étudiant existant
            etudiantExist.setNom(nouvelEtudiant.getNom());
            etudiantExist.setPrenom(nouvelEtudiant.getPrenom());
            etudiantExist.setDateNaissance(nouvelEtudiant.getDateNaissance());
        } else {
            throw new IllegalArgumentException("Étudiant non trouvé.");
        }
    }

    // Méthode pour supprimer un étudiant
    public void supprimerEtudiant(String id) {
        Etudiant etudiant = getEtudiantById(id);
        if (etudiant != null) {
            etudiants.remove(etudiant); // Supprime l'étudiant de la liste
        } else {
            throw new IllegalArgumentException("Étudiant non trouvé.");
        }
    }

    // Méthode pour obtenir un étudiant par ID
    public Etudiant getEtudiantById(String id) {
        for (Etudiant e : etudiants) {
            if (e.getIdEtudiant().equals(id)) {
                return e; // Retourne l'étudiant correspondant
            }
        }
        return null; // Retourne null si l'étudiant n'est pas trouvé
    }

    // Méthode pour obtenir la liste des étudiants
    public List<Etudiant> getListeEtudiants() {
        return etudiants; // Retourne la liste des étudiants
    }

    // Ajoutez d'autres méthodes si nécessaire...
}