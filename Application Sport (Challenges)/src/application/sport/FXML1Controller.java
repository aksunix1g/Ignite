/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.sport;

import Entities.Challenges;
import Utils.Connexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Touihri
 */
public class FXML1Controller implements Initializable {

    @FXML
    private VBox Vbox;
    @FXML
    private AnchorPane bck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
        
               for (Challenges challenge : ChallengesList) {
                   HBox hb = new HBox();
                   ImageView iv2 = new ImageView();
                   File f = new File("C:\\xampp\\php\\www\\Ignite\\imgs\\"+challenge.getImage());
                   iv2.setImage(new Image(f.toURI().toString()));
                   Label Title= new Label(challenge.getNom());
                   Button btn= new Button("Open");
                    hb.getChildren().addAll(iv2,Title,btn);
                    Vbox.getChildren().addAll(hb);
                    
                    btn.setOnAction(
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event
                    ) {
                        Parent fxml;

        try {

            fxml = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            bck.getChildren().removeAll();
            bck.getChildren().setAll(fxml);

        } catch (IOException ex) {
            ex.getMessage();
        }
                    }
                }
                );
                   
               }
               
    }
        // TODO
        
    
    
}
