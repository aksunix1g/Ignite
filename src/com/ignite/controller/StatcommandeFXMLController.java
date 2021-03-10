/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.controller;

import com.ignite.service.ServiceStat;
import com.ignite.utils.DataBase;
import com.jfoenix.controls.JFXButton;
import ignite.Ignite;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mouhamed
 */
public class StatcommandeFXMLController implements Initializable {
    ServiceStat service_stat= new ServiceStat();
        ObservableList<PieChart.Data> stat=FXCollections.observableArrayList();

    ArrayList<String> libelle = new ArrayList<String>();
    ArrayList<Integer> quantiteDispo = new ArrayList<Integer>();
    @FXML
    private JFXButton Accueil;
    @FXML
    private JFXButton Commande;
    @FXML
    private JFXButton Statistique;
    @FXML
    private PieChart piechart;
    @FXML
 private BarChart<String, Integer> barchart;
    @FXML
    private Pane pnlStatus;
    @FXML
    private Label lblStatusMini;
    @FXML
    private Label lblStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
           
            
            Statement stm =  DataBase.getInstance().getConnection().createStatement();
               ResultSet rest=stm.executeQuery("select user , idproduit from commande");
              
               while(rest.next())
               {
                   libelle.add(rest.getString("user"));
                   quantiteDispo.add(rest.getInt("idproduit"));
                   stat.add(new PieChart.Data(rest.getString("user"), rest.getInt("idproduit")));
               }
    }
    catch (SQLException ex) {
            Logger.getLogger(StatcommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setAnimated(true);
        piechart.maxHeight(1000);
       piechart.setData(stat);
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
  
        for (Map.Entry<String,Integer> i: service_stat.getStat().entrySet() ) {
            String nom=i.getKey();
            int nbr=i.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(nom, nbr);
            series1.getData().add(d);
        }
        
        barchart.getData().addAll(series1);
       
    }    

    @FXML
    private void ButtonAccuiel(ActionEvent event) {
    }

    @FXML
    private void ButtonCommande(ActionEvent event) throws IOException {
                Ignite.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/AjouterCommendeFXML.fxml"))));

    }

    @FXML
    private void ButtonStaistique(ActionEvent event) {
          try {              
            Ignite.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/statcommandeFXML.fxml"))));
        } catch (IOException ex) {
            Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
