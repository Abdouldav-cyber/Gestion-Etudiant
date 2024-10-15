package controller;

import model.Cours;

import java.util.ArrayList;
import java.util.List;

public class CoursController {
    private List<Cours> coursList; // Liste pour stocker les cours

    public CoursController() {
        this.coursList = new ArrayList<>();
    }

    // Méthode pour ajouter un cours
    public void ajouterCours(Cours cours) {
        if (cours != null && cours.getId() != null && !cours.getId().isEmpty()) {
            coursList.add(cours); // Ajoute le cours à la liste
        } else {
            throw new IllegalArgumentException("Le cours doit avoir un ID valide.");
        }
    }

    // Méthode pour modifier un cours
    public void modifierCours(String id, Cours nouveauCours) {
        Cours coursExist = getCoursById(id);
        if (coursExist != null) {
            // Remplace les informations du cours existant
            coursExist.setNom(nouveauCours.getNom());
            coursExist.setDescription(nouveauCours.getDescription());
        } else {
            throw new IllegalArgumentException("Cours non trouvé.");
        }
    }

    // Méthode pour supprimer un cours
    public void supprimerCours(String id) {
        Cours cours = getCoursById(id);
        if (cours != null) {
            coursList.remove(cours); // Supprime le cours de la liste
        } else {
            throw new IllegalArgumentException("Cours non trouvé.");
        }
    }

    // Méthode pour obtenir un cours par ID
    public Cours getCoursById(String id) {
        for (Cours c : coursList) {
            if (c.getId().equals(id)) {
                return c; // Retourne le cours correspondant
            }
        }
        return null; // Retourne null si le cours n'est pas trouvé
    }

    // Méthode pour obtenir la liste des cours
    public List<Cours> getListeCours() {
        return coursList; // Retourne la liste des cours
    }

    // Ajoutez d'autres méthodes si nécessaire...
}