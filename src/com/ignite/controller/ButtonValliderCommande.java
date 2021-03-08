/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.controller;

import com.ignite.entity.Commande;
import com.ignite.service.CommandeService;
import com.sun.prism.impl.Disposer;
import ignite.Ignite;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * @author said
 */
public class ButtonValliderCommande extends TableCell<Disposer.Record, Boolean> {
   CommandeService service_commande= new CommandeService();
        final Button cellButton = new Button("modifier");


    public ButtonValliderCommande() {
    
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    try {
                        // get Selected Item
                        Commande Commandecourant = (Commande) ButtonValliderCommande.this.getTableView().getItems().get(ButtonValliderCommande.this.getIndex());
                        //remove selected item from the table list
                        
                        System.out.println(Commandecourant);
                        Commande.setId_courant(Commandecourant.getId());
                        try {
                            service_commande.valide_btn(Commandecourant);
                        } catch (SQLException ex) {
                            Logger.getLogger(ButtonValliderCommande.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   Ignite.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/AfficherCommandeFXML.fxml"))));
                    } catch (IOException ex) {
                        Logger.getLogger(ButtonValliderCommande.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    
}
