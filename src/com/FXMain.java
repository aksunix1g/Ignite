/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author charf
 */
public class FXMain extends Application{
    
    
    
    
    
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/FXMLAffichageProduit.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Ignite v0.0");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
         Stage stageCrud = new Stage();
         Parent rootCrud = null;
         rootCrud = FXMLLoader.load(getClass().getResource("/gui/FXMLCrudProduit.fxml"));
                    
               
         stageCrud.setTitle("cruds produit");
         stageCrud.setScene(new Scene(rootCrud,600,400));
         stageCrud.show();
    }
}
