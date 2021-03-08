/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.controller;

import com.ignite.entity.Commande;
import com.ignite.service.CommandeService;
import com.ignite.utils.DataBase;
import com.sun.prism.impl.Disposer;
import ignite.Ignite;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author mouhamed
 */
public class AfficherCommandeFXMLController implements Initializable {

    @FXML
    private Button btnaccueil;
    @FXML
    private Button btnconsultecommande;
    @FXML
    private Pane pane;
    @FXML
    private Label labelpath;
    @FXML
    private Label labeltitle;
    @FXML
    private TableView<Commande> tableview;
    @FXML
    private TableColumn<Commande, String> tid;
    @FXML
    private TableColumn<Commande, String> tidproduit;
    @FXML
    private TableColumn<Commande, String> tuser;
    @FXML
    private TableColumn<Commande, String> tdatecom;
    @FXML
    private TableColumn<Commande, String> tprixtotal;
    @FXML
    private TableColumn<Commande, String> tvalide;
   CommandeService service_cmd=new CommandeService();
     private Connection con;
    @FXML
    private Button AjoutercommandeButton;
    public AfficherCommandeFXMLController() {
        con = DataBase.getInstance().getConnection();

    }
    ObservableList<Commande> list =FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              try {
            for (Commande p:service_cmd.readAllCommande())
            {
                list.add(p);
                
            }
                 tid.setCellValueFactory(new PropertyValueFactory<>("id"));
                   tdatecom.setCellValueFactory(new PropertyValueFactory<>("datecom"));
                    
                    tprixtotal.setCellValueFactory(new PropertyValueFactory<>("prixtotale"));
                tuser.setCellValueFactory(new PropertyValueFactory<>("user"));
                    tidproduit.setCellValueFactory(new PropertyValueFactory<>("idproduit"));
                    tvalide.setCellValueFactory(new PropertyValueFactory<>("valide"));
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         TableColumn col_action2 = new TableColumn<>("supprimer");
        tableview.getColumns().add(col_action2);

        col_action2.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action2.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

    
                    
             @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonDeleteCommande();
            }
        
        }); 
                                                 
        TableColumn col_action = new TableColumn<>("valider commande");
        tableview.getColumns().add(col_action);
        
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

    
                    
             @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonValliderCommande();
            }
        
        });       
                        tableview.setItems(list);

    }    

    @FXML
    private void accueil(ActionEvent event) {
        
    }

    @FXML
    private void consultenseignant(ActionEvent event) {
    }

    @FXML
    private void Ajoutercommande(ActionEvent event) throws IOException {
                                                           Ignite.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/AjouterCommendeFXML.fxml"))));              

    }
    
}
