package com.mz.miniprojetandroid.Models;

public class Medicament {
    private int id;
    private String libelle;
    private String categorie;
    private String prix;
    private String quantite;
    private String date;

    public Medicament() {
    }

    public Medicament(String libelle, String categorie, String prix, String quantite, String date) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", date='" + date + '\'' +
                '}';
    }
}
