/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ignite;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mouhamed
 */
public class Ignite extends Application {
     
    private Stage stage;
    private static Ignite instance;
    private Scene scene;

    public Ignite() throws IOException, InterruptedException {
        instance = this;
 

  scene = new Scene(FXMLLoader.load(getClass().getResource("/com/ignite/gui/AfficherCommandeFXML.fxml")));

    }

    public static Ignite getInstance() {
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        stage.setScene(this.scene);
        stage.initStyle(StageStyle.DECORATED);// reduire et fermer decoredet* - 
        stage.centerOnScreen();
        stage.show();
    }

    public void changescene(Scene scene) {
        this.scene = scene;
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
