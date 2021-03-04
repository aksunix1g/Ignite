

package org.xemacscode.demo;

public class Commande {
    private int id;
    private String NomProd;
    private String NomClient;
    private int NumCmd;
    private int Qte;

    public Commande (int id, String NomProd, String NomClient, int NumCmd, int Qte) {
        this.id = id;
        this.NomProd = NomProd;
        this.NomClient = NomClient;
        this.NumCmd = NumCmd;
        this.Qte = Qte;
    }

    public int getId() {
        return id;
    }

    public String getNomProd() {
        return NomProd;
    }

    public String getNomClient() {
        return NomClient;
    }

    public int getNumCmd() {
        return NumCmd;
    }

    public int getQte() {
        return Qte;
    }
    
}
