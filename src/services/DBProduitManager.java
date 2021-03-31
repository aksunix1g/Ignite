package services;

import entities.Produit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBProduitManager {

    Connection connection = DBManager.getInstance().getConnection();

    public DBProduitManager() {
    }

    public void ajouterProduit(Produit produit) {

        try {
            String requete = "insert into produit values ('" + produit.getIdProduit() + "','" + produit.getNomProduit()
                    + "','" + produit.getImageProduit() + "','" + produit.getCategorieProduit() + "','" + produit.getPrixProduit() + "','"
                    + produit.getQuantiteProduit() + "','" + produit.getDescProduit() + "');";
            Statement st = connection.createStatement();
            st.executeUpdate(requete);
            System.out.println("produit ajoutee");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void supprimerProduit(int idProduit) {

        try {
            String requete = "delete from produit where idProduit='" +idProduit+ "';";
            Statement st = connection.createStatement();
            st.executeUpdate(requete);
            System.out.println("produit supprime");

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
    
    public void modifierProduit(Produit produit,String nom ,String image,int categorie,float prix,int quantite,String desc){
        try {
			String requete = "update  produit set nomProduit='"+nom+"',imageProduit='"+image+"',categorieProduit='"+
					categorie+"',prixProduit='"+prix+"',quantiteProduit='"+quantite+"',descriptionProduit='"+desc
                                +"' where idProduit="+produit.getIdProduit()+";";
			Statement st =connection.createStatement();
			st.executeUpdate(requete);
			System.out.println("produit modifie");

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public ArrayList<Produit> afficherProduit(){
        ArrayList<Produit> listProduits = new ArrayList<Produit>();
		
		try {
			String requete = "select * from produit ;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(requete);
			while(rs.next()) {
				listProduits.add(new Produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getFloat(5),rs.getInt(6),rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProduits;
    }
    
     public ArrayList<Produit> afficherProduittriDesc(){
        ArrayList<Produit> listProduits = new ArrayList<Produit>();
		
		try {
			String requete = "select * from produit order by prixProduit desc ;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(requete);
			while(rs.next()) {
				listProduits.add(new Produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getFloat(5),rs.getInt(6),rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProduits;
    }
       public ArrayList<Produit> afficherProduittriCroi(){
        ArrayList<Produit> listProduits = new ArrayList<Produit>();
		
		try {
			String requete = "select * from produit order by prixProduit  ;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(requete);
			while(rs.next()) {
				listProduits.add(new Produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getFloat(5),rs.getInt(6),rs.getString(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listProduits;
    }
    
    
    public Produit getProduitParId(int id){
        
		Produit prodSelectionne = null;
		try {
			String requete = "select * from produit ;";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(requete);
			while(rs.next()) {
                            if(rs.getInt(1)==id){
                                prodSelectionne=new Produit(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4),rs.getFloat(5),rs.getInt(6),rs.getString(7));
                            }
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prodSelectionne;
    
    }

}
