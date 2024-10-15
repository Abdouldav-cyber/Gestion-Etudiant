package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etudiant {
    private String idEtudiant;  // Identifiant de l'étudiant
    private String nom;         // Nom de l'étudiant
    private String prenom;      // Prénom de l'étudiant
    private Date dateNaissance; // Date de naissance de l'étudiant
    private List<Cours> coursSuivis; // Liste des cours suivis par l'étudiant

    // Constructeur
    public Etudiant(String idEtudiant, String nom, String prenom, Date dateNaissance) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.coursSuivis = new ArrayList<>(); // Initialise la liste des cours suivis
    }

    // Getters et setters
    public String getIdEtudiant() {
        return idEtudiant; // Retourne l'ID de l'étudiant
    }

    public void setIdEtudiant(String idEtudiant) {
        this.idEtudiant = idEtudiant; // Met à jour l'ID de l'étudiant
    }

    public String getNom() {
        return nom; // Retourne le nom de l'étudiant
    }

    public void setNom(String nom) {
        this.nom = nom; // Met à jour le nom de l'étudiant
    }

    public String getPrenom() {
        return prenom; // Retourne le prénom de l'étudiant
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom; // Met à jour le prénom de l'étudiant
    }

    public Date getDateNaissance() {
        return dateNaissance; // Retourne la date de naissance de l'étudiant
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance; // Met à jour la date de naissance de l'étudiant
    }

    public List<Cours> getCoursSuivis() {
        return coursSuivis; // Retourne la liste des cours suivis
    }

    // Méthodes pour gérer les cours suivis
    public void ajouterCours(Cours cours) {
        coursSuivis.add(cours); // Ajoute un cours à la liste
    }

    public void supprimerCours(Cours cours) {
        coursSuivis.remove(cours); // Supprime un cours de la liste
    }
}