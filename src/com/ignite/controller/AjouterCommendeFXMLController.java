/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.controller;

import com.ignite.entity.Commande;
import com.ignite.service.CommandeService;
import ignite.Ignite;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mouhamed
 */
public class AjouterCommendeFXMLController implements Initializable {

    @FXML
    private Button btnaccueil;
    @FXML
    private Button btnconsultecommande;
    @FXML
    private Button AjoutercommandeButton;
    @FXML
    private Pane pane;
    @FXML
    private Label labelpath;
    @FXML
    private Label labeltitle;
    @FXML
    private Pane pane1;
    @FXML
    private Button AjoutercommandeButton1;
    @FXML
    private TextField idproduit;
    @FXML
    private Label labeltitle1;
    @FXML
    private TextField prixtotale;
    @FXML
    private TextField iduser;
    @FXML
    private Label labeltitle11;
   CommandeService service_cmd=new CommandeService();
    @FXML
    private Label ijlh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accueil(ActionEvent event) {

    }

    @FXML
    private void Ajoutercommande(ActionEvent event) throws SQLException {
     String  valide="en cours";
               java.util.Date date = new java.util.Date();
            java.sql.Date dateSql = new java.sql.Date(date.getTime());
             Commande C = new Commande();
        C.setIdproduit(Integer.parseInt(idproduit.getText()));
        C.setPrixtotale(Double.parseDouble(prixtotale.getText()));
        C.setValide(valide);
        C.setUser(Integer.parseInt(iduser.getText()));
        C.setDatecom(dateSql);
         service_cmd.AjoutCommande(C);
    }

    @FXML
    private void consultecommande(ActionEvent event) throws IOException {
                                                   Ignite.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/AfficherCommandeFXML.fxml"))));              

    }
    
}
