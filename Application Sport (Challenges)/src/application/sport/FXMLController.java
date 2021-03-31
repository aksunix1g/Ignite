/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.sport;

import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Touihri
 */
public class FXMLController implements Initializable {

    @FXML
    private Label Label_Min;
    @FXML
    private Label Label_Sec;
    @FXML
    private Label Label_MiliSec;
    @FXML
    private Button BtnStart;
    @FXML
    private Button BtnStop;
    @FXML
    private Button BtnPause;
    long min = 30;
    long sec = 00;
    long milsec = 00;
    long tempmin, tempsec, tempmilsec;
    AnimationTimer timer = new MyTimer();
    @FXML
    private Button BtnResume;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BtnResume.setVisible(false);
        setBloc(min, sec, milsec);
    }

    private class MyTimer extends AnimationTimer {

        @Override
        public void handle(long now) {

            doHandle();
        }

        private void doHandle() {

            try {
                milsec -= 1;
                setBloc(min, sec, milsec);
                sleep(15);
                if (milsec <= 0) {
                    sec--;
                    milsec = 59;
                }
                if (sec <= 0) {
                    min--;
                    min = 59;
                }
                if (min <= 0) {
                    stop();
                    System.out.println("Countdown stopped");
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void ButtonStart(ActionEvent event) {
        min = 29;
        sec = 59;
        milsec = 59;
        timer.start();
    }

    @FXML
    private void ButtonStop(ActionEvent event) {
        timer.stop();
        setBloc(0, 0, 0);
    }

    @FXML
    private void ButtonPause(ActionEvent event) {
        tempmin = min;
        tempsec = sec;
        tempmilsec = milsec;
        BtnPause.setVisible(false);
        BtnResume.setVisible(true);
        timer.stop();
    }

    @FXML
    private void ButtonResume(ActionEvent event) {
        min=tempmin;
        sec=tempsec;
        milsec=tempmilsec;
        BtnPause.setVisible(true);
        BtnResume.setVisible(false);
        timer.start();
    }

    void setBloc(long min, long sec, long milsec) {
        Label_Min.setText(String.valueOf(min));
        Label_Sec.setText(String.valueOf(sec));
        Label_MiliSec.setText(String.valueOf(milsec));
    }

}
