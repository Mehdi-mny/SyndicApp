package com.example.syndicapp;

public class Reclamation {
    private String titre;
    private String description;
    public Reclamation() {
    }

    public Reclamation(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
