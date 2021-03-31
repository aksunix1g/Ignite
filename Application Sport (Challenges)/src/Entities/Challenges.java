/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author Touihri
 */
public class Challenges {
    
    private int ID ;
    private String	Nom;
    private String Begin_Date,End_Date;
    private String Image;
    private Image Images;

    public Challenges(int ID, String Nom, String Begin_Date,String  End_Date,String Image) {
        this.ID = ID;
        this.Nom = Nom;
        this.Begin_Date = Begin_Date;
        this.End_Date = End_Date;
        this.Image=Image;
    }

    public Challenges() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getBegin_Date() {
        return Begin_Date;
    }

    public void setBegin_Date(String Begin_Date) {
        this.Begin_Date = Begin_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String End_Date) {
        this.End_Date = End_Date;
    }
    
    
    
}
