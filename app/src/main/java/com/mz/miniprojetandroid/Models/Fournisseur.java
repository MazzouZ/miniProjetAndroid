package com.mz.miniprojetandroid.Models;

import java.util.ArrayList;

public class Fournisseur {
    private int id;
    private String nom;
    private String email;
    private String adresse;
    private String telephone;
    private ArrayList<Medicament> medicaments;

    public Fournisseur( String nom, String email, String adresse, String telephone) {
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public Fournisseur() {
    }

    public ArrayList<Medicament> getMedicaments() {
        return medicaments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return nom;
    }
}
