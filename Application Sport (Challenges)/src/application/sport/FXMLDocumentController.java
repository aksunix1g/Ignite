/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.sport;

import Entities.Challenges;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.Connexion;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Touihri
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfTitre;
    @FXML
    private DatePicker tfDate_Lancement;
    @FXML
    private DatePicker tfDate_Fin;
    @FXML
    private TableView<Challenges> TvChallenge;
    @FXML
    private TableColumn<Challenges, Integer> ClID;
    @FXML
    private TableColumn<Challenges, String> ClNom;
    @FXML
    private TableColumn<Challenges, String> ClDate_Lancement;
    @FXML
    private TableColumn<Challenges, String> ClDate_Fin;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    private String  listview,Path; 
    @FXML
    private ImageView ImageView;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       if (event.getSource() == btnInsert ){
           InsertChallenges();
        }else if (event.getSource() == btnDelete){
            DeleteChallenges();
        }else if(event.getSource() == btnUpdate)
        { UpdateChallenges();}
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showChallenges();
    }    
       public ObservableList<Challenges> getChallengesList() {
        ObservableList<Challenges> ChallengesList= FXCollections.observableArrayList();
        Connection conn= Connexion.getInstance().getConnection();
        
        String query= "SELECT * FROM CHALLENGE";
       
    Statement st ;
    ResultSet rs ;

        try{
                st= conn.createStatement();
                rs = st.executeQuery(query);
                Challenges challenges ;
            while (rs.next()){
            challenges = new Challenges (rs.getInt("ID"),rs.getString("Nom"),rs.getString("Begin_Date"),rs.getString("End_Date"),rs.getString("Image"));
                ChallengesList.add(challenges);
            }
            }catch (Exception ex){
                               ex.printStackTrace();             }
               return ChallengesList;
    }
    public void showChallenges() {
        ObservableList<Challenges> list = getChallengesList();
       ClID.setCellValueFactory(new PropertyValueFactory<Challenges, Integer>("ID"));
       ClNom.setCellValueFactory(new PropertyValueFactory<Challenges, String>("Nom"));
       ClDate_Lancement.setCellValueFactory(new PropertyValueFactory<Challenges, String>("Begin_Date"));
       ClDate_Fin.setCellValueFactory(new PropertyValueFactory<Challenges, String>("End_Date"));
      
       
       
       TvChallenge.setItems(list);
}
 
    private void InsertChallenges(){
        try {
            System.out.println(tfDate_Lancement.getValue());
            
            String query = "INSERT INTO `challenge`(`Nom`, `Begin_Date`, `End_Date`,`Image`) VALUES ('" + tfTitre.getText() +"', '" + tfDate_Lancement.getValue() + "','" + tfDate_Fin.getValue() +"','"+ listview +"')";
            executeQuery(query);
            String PathTo= "C:\\xampp\\php\\www\\Ignite\\imgs\\"+listview;
            //Paths.get("").toAbsolutePath().toString();
            File org=new File(Path);
            File news=new File(PathTo);
            Files.copy(org.toPath(), news.toPath(), StandardCopyOption.REPLACE_EXISTING);
            showChallenges();
        } catch (IOException ex) {
           
        }

    }
    private void UpdateChallenges(){
        try {
            String query = "UPDATE CHALLENGE SET Nom = '" + tfTitre.getText() + "',Begin_Date ='" + tfDate_Lancement.getValue() + "',End_Date ='" + tfDate_Fin.getValue() +"',Image ='"+ listview +"'   WHERE ID = " + tfID.getText() +" ";
            executeQuery(query);
            String PathTo= "C:\\xampp\\php\\www\\Ignite\\imgs\\"+listview;
            //Paths.get("").toAbsolutePath().toString();
            File org=new File(Path);
            File news=new File(PathTo);
            Files.copy(org.toPath(), news.toPath(), StandardCopyOption.REPLACE_EXISTING);
            showChallenges();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    private void DeleteChallenges() {
         String query = "DELETE FROM CHALLENGE WHERE ID = " + tfID.getText() +"" ;
        executeQuery(query);
        showChallenges();
        
    }
    
private void executeQuery (String query){
    Connection conn= Connexion.getInstance().getConnection();
    Statement st ;
    try {
        st = conn.createStatement();
        st.executeUpdate(query);
    }catch(Exception ex){
        ex.printStackTrace();
    }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Challenges challenge = TvChallenge.getSelectionModel().getSelectedItem();
        tfID.setText("" +challenge.getID());
        tfTitre.setText(challenge.getNom());
        LocalDate l = LocalDate.parse(challenge.getEnd_Date());
        LocalDate L1 = LocalDate.parse(challenge.getBegin_Date());
        tfDate_Lancement.setValue(L1);
        tfDate_Fin.setValue(l);
        File f = new File("C:\\xampp\\php\\www\\Ignite\\imgs\\"+challenge.getImage());
       ImageView.setImage(new Image(f.toURI().toString()));
       // System.out.println("url: "+challenge.getImage());

    }

    @FXML
    private void loadimage(ActionEvent event) {
          FileChooser fc = new FileChooser();
    fc.getExtensionFilters().addAll(
            new ExtensionFilter("PDF Files","*.PDF"),
            new ExtensionFilter("PNG files (*.png)", "*.png"),
            new ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
            new ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg") 
    );
    File selectedFiles = fc.showOpenDialog(null);
    if (selectedFiles !=null){
        
        listview=selectedFiles.getName();
        Path=selectedFiles.getAbsolutePath();
            System.out.println(listview);
         //   ImageView.setImage(new Image(Path));
          File f = new File(Path);
       ImageView.setImage(new Image(f.toURI().toString()));
        }else{
        System.out.println("file is not valid");
    }
    }
 
}


    
    

