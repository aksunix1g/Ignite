/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.service;

import com.ignite.Iservice.ICommandeService;
import com.ignite.entity.Commande;
import com.ignite.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dali
 */
public class CommandeService implements ICommandeService<Commande> {

    private Connection con;
    private Statement ste;

    public CommandeService() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void AjoutCommande(Commande commande) throws SQLException {
        try {

            String query = "INSERT INTO `commande`(`idproduit`,`user`,`datecom`,`prixtotale`,`valide`) values (?,?,?,?,?)";
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, commande.getIdproduit());
            ps.setString(2, commande.getUser());
            ps.setDate(3, commande.getDatecom());
            ps.setDouble(4, commande.getPrixtotale());
            ps.setString(5, commande.getValide());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public boolean deleteCommande(int id) {
               String requete = "DELETE from Commande where id=?";
            try {
            PreparedStatement st =con.prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("Commande Supprimee.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateCommande(int id) throws SQLException {
        String requete = "UPDATE commande set prixtotale=? WHERE ID='" + id + "'";

        PreparedStatement st = con.prepareStatement(requete);
        st.setString(1, "5");
        st.executeUpdate();
        System.out.println(" Commande changee");
        return true;
    }

    @Override
    public List<Commande> readAllCommande() throws SQLException {
        Statement st = con.createStatement();
        List<Commande> arr = new ArrayList<>();

        ste = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM commande");
        while (rs.next()) {
            Commande c = new Commande();
            c.setId(rs.getInt("id"));
            c.setIdproduit(rs.getInt("idproduit"));
            c.setUser(rs.getString("user"));
            c.setDatecom(rs.getDate("datecom"));
            c.setValide(rs.getString("valide"));
            c.setPrixtotale(rs.getDouble("prixtotale"));
            arr.add(c);
        }

        return arr;
    }

    @Override
    public void AjoutCommandeproduit(int c, int p) throws SQLException {
        try {

            String query = "INSERT INTO `commande` (`product_id`) VALUES ('?');";
            PreparedStatement ps;
            ps = DataBase.getInstance().getConnection().prepareStatement(query);

            ps.setInt(1, p);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static java.util.Date convertirDate(int d) {
        Timestamp stamp = new Timestamp(d);
        java.util.Date date = new java.util.Date(stamp.getTime());
        return (date);
    }
     @Override
  public void valide_btn(Commande a) throws SQLException {
        String query = "update commande set valide=? where id=? ";
        PreparedStatement ps;
        ps = DataBase.getInstance().getConnection().prepareStatement(query);

        ps.setString(1,"valide");
        ps.setInt(2, a.getId());

        ps.executeUpdate();

    }
}
