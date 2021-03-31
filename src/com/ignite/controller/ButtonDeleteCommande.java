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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author mouhamed
 */
public class ButtonDeleteCommande extends TableCell<Disposer.Record, Boolean> {

    final Button cellButton = new Button("supprimer");
    CommandeService service_pr = new CommandeService();

    ButtonDeleteCommande() {

        Notifications notificationBuilder = Notifications.create()
                .title("Important !")
                .text("Votre Commande été Supprimer")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked Notification");
                    }
                });

        //Action when the button is pressed
        cellButton.setOnAction((ActionEvent t) -> {
            try {
                // get Selected Item
                Commande Commandecourant = (Commande) ButtonDeleteCommande.this.getTableView().getItems().get(ButtonDeleteCommande.this.getIndex());
                //remove selected item from the table list
                ObservableList<Commande> list = FXCollections.observableArrayList();
                try {
                    notificationBuilder.showConfirm();
                    for (Commande c : service_pr.readAllCommande()) {
                        list.add(c);

                    }
                    System.out.println(Commandecourant);

                    list.remove(Commandecourant);

                } catch (SQLException ex) {
                    Logger.getLogger(ButtonDeleteCommande.class.getName()).log(Level.SEVERE, null, ex);
                }
                service_pr.deleteCommande(Commandecourant.getId());
                System.out.println(list);
                Ignite.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/AfficherCommandeFXML.fxml"))));
            } catch (IOException ex) {
                Logger.getLogger(ButtonDeleteCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        }
    }

}
