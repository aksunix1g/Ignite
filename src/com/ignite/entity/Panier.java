/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.entity;

/**
 *
 * @author mouhamed
 */
public class Panier {
    private int id;
    private int quantite;
    private String nomProd;
    private float prixtotale;
        private float prixParProd;

    public Panier(int id, int quantite, String nomProd, float prixtotale, float prixParProd) {
        this.id = id;
        this.quantite = quantite;
        this.nomProd = nomProd;
        this.prixtotale = prixtotale;
        this.prixParProd = prixParProd;
    }

    public Panier() {
    }

    public Panier(int quantite, String nomProd, float prixtotale, float prixParProd) {
        this.quantite = quantite;
        this.nomProd = nomProd;
        this.prixtotale = prixtotale;
        this.prixParProd = prixParProd;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", quantite=" + quantite + ", nomProd=" + nomProd + ", prixtotale=" + prixtotale + ", prixParProd=" + prixParProd + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public float getPrixtotale() {
        return prixtotale;
    }

    public void setPrixtotale(float prixtotale) {
        this.prixtotale = prixtotale;
    }

    public float getPrixParProd() {
        return prixParProd;
    }

    public void setPrixParProd(float prixParProd) {
        this.prixParProd = prixParProd;
    }

    
    
    
    
}
