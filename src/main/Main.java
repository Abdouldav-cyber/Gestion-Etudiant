package Cours; // ou le package approprié

class Cours {
    private String nom;
    private String description;

    // Constructeur
    public Cours(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Autres méthodes
}
