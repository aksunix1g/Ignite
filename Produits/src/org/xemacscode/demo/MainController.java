/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MainController implements Initializable {
    
    
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDesc;
    @FXML
    private TextField tfQte;
    @FXML
    private TextField tfPrix;
    @FXML
    private TableView<Produits> Listprod;
    @FXML
    private TableColumn<Produits, Integer> colId;
    @FXML
    private TableColumn<Produits, String> colNom;
    @FXML
    private TableColumn<Produits, String> colDesc;
    @FXML
    private TableColumn<Produits, Integer> colQte;
    @FXML
    private TableColumn<Produits, Integer> colPrix;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showProduits();
    }
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ignite", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Produits> getProduitsList(){
        ObservableList<Produits> ProduitList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM produits";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Produits books;
            while(rs.next()){
                books = new Produits(rs.getInt("id"), rs.getString("nom"), rs.getString("description"), rs.getInt("quantite"),rs.getInt("prix"));
                ProduitList.add(books);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ProduitList;
    }
    
    public void showProduits(){
        ObservableList<Produits> list = getProduitsList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Produits, String>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<Produits, String>("description"));
        colQte.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produits, Integer>("prix"));
        
        Listprod.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO produits VALUES (" + tfId.getText() + ",'" + tfNom.getText() + "','" + tfDesc.getText() + "',"
                + tfQte.getText() + "," + tfPrix.getText() + ")";
        executeQuery(query);
        showProduits();
    }
    private void updateRecord(){
        String query = "UPDATE  produits SET nom  = '" + tfNom.getText() + "', description = '" + tfDesc.getText() + "', quantite = " +
                tfQte.getText() + ", prix = " + tfPrix.getText() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showProduits();
    }
    private void deleteButton(){
        String query = "DELETE FROM produits WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showProduits();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
        
    
}
