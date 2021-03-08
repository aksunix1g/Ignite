

package com.ignite.entity;

import java.sql.Date;

public class Commande {
   private static int id_courant ;

private int id;
private int idproduit;
private int user;
private Date datecom;
private Double prixtotale;
private String valide;


    public static int getId_courant() {
        return id_courant;
    }

    public static void setId_courant(int id_courant) {
        Commande.id_courant = id_courant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Date getDatecom() {
        return datecom;
    }

    public void setDatecom(Date datecom) {
        this.datecom = datecom;
    }

    public double getPrixtotale() {
        return prixtotale;
    }

    public void setPrixtotale(double prixtotale) {
        this.prixtotale = prixtotale;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public Commande() {
    }

    public Commande(int id, int idproduit, int user, Date datecom, double prixtotale, String valide) {
        this.id = id;
        this.idproduit = idproduit;
        this.user = user;
        this.datecom = datecom;
        this.prixtotale = prixtotale;
        this.valide = valide;
    }

    public Commande(int idproduit, int user, Date datecom, double prixtotale, String valide) {
        this.idproduit = idproduit;
        this.user = user;
        this.datecom = datecom;
        this.prixtotale = prixtotale;
        this.valide = valide;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", idproduit=" + idproduit + ", user=" + user + ", datecom=" + datecom + ", prixtotale=" + prixtotale + ", valide=" + valide + '}';
    }

    public Commande(int idproduit, int user, Double prixtotale) {
        this.idproduit = idproduit;
        this.user = user;
        this.prixtotale = prixtotale;
    }

   
  
  
  
}
