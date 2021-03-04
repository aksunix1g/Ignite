/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xemacscode.demo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Produits {
    private int id;
    private String nom;
    private String description;
    private int quantite;
    private int prix;

    public Produits(int id, String nom, String description, int quantite, int prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getPrix() {
        return prix;
    }
    
}
