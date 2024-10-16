package model;

import java.sql.Date;

public class Resultat {
    private String id;
    private String idEtudiant;
    private String idCours;
    private double note;
    private Date date;

    public Resultat(String id, String idEtudiant, String idCours, double note, Date date) {
        this.id = id;
        this.idEtudiant = idEtudiant;
        this.idCours = idCours;
        this.note = note;
        this.date = date;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public String getIdEtudiant() {
        return idEtudiant;
    }

    public String getIdCours() {
        return idCours;
    }

    public double getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;

}}