package model;

import java.sql.Date; // Utilisation correcte de java.sql.Date pour la compatibilité avec JDBC
import java.util.ArrayList;
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
        return idEtudiant;
    }

    public void setIdEtudiant(String idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Cours> getCoursSuivis() {
        return coursSuivis;
    }

    // Méthodes pour gérer les cours suivis
    public void ajouterCours(Cours cours) {
        coursSuivis.add(cours);
    }

    public void supprimerCours(Cours cours) {
        coursSuivis.remove(cours);
    }
}
