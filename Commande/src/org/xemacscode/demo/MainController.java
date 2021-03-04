
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
 * 
 */
public class MainController implements Initializable {
    
    
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNomProd;
    @FXML
    private TextField tfNomClient;
    @FXML
    private TextField tfNumCmd;
    @FXML
    private TextField tfQte;
    @FXML
    private TableView<Commande> tvCommande;
    @FXML
    private TableColumn<Commande, Integer> colId;
    @FXML
    private TableColumn<Commande, String> colNomProd;
    @FXML
    private TableColumn<Commande, String> colNomClient;
    @FXML
    private TableColumn<Commande, Integer> colNumCmd;
    @FXML
    private TableColumn<Commande, Integer> colQte;
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
        showCommande();
    }
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/Table_Commande", "root","root");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Commande> getCommandeList(){
        ObservableList<Commande> CommandeList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Commande";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Commande Commande;
            while(rs.next()){
                Commande = new Commande(rs.getInt("id"), rs.getString("NomProd"), rs.getString("NomClient"), rs.getInt("NumCmd"),rs.getInt("Qte"));
                CommandeList.add(Commande);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return CommandeList;
    }
    
    public void showCommande(){
        ObservableList<Commande> list = getCommandeList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
        colNomProd.setCellValueFactory(new PropertyValueFactory<Commande, String>("NomProd"
                + ""));
        colNomClient.setCellValueFactory(new PropertyValueFactory<Commande, String>("NomClient"));
        colNumCmd.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("NumCmd"));
        colQte.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("Qte"));
        
        tvCommande.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO Commande VALUES (" + tfId.getText() + ",'" + tfNomProd.getText() + "','" + tfNomClient.getText() + "',"
                + tfNumCmd.getText() + "," + tfQte.getText() + ")";
        executeQuery(query);
        showCommande();
    }
    private void updateRecord(){
        String query = "UPDATE  Commande SET NomProd  = '" + tfNomProd.getText() + "', NomClient = '" + tfNomClient.getText() + "', NumCmd = " +
                tfNumCmd.getText() + ", Qte = " + tfQte.getText() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showCommande();
    }
    private void deleteButton(){
        String query = "DELETE FROM Commande WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showCommande();
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
