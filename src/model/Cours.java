package model;

public class Cours {
    private String id;           // Identifiant du cours
    private String nom;          // Nom du cours
    private String description;   // Description du cours

    // Constructeur
    public Cours(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    // Getters et setters
    public String getId() {
        return id; // Retourne l'ID du cours
    }

    public void setId(String id) {
        this.id = id; // Met à jour l'ID du cours
    }

    public String getNom() {
        return nom; // Retourne le nom du cours
    }

    public void setNom(String nom) {
        this.nom = nom; // Met à jour le nom du cours
    }

    public String getDescription() {
        return description; // Retourne la description du cours
    }

    public void setDescription(String description) {
        this.description = description; // Met à jour la description du cours
    }
}