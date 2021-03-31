/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Challenges;
import Services.IServiceChallenges;
import Utils.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;


/**
 *
 * @author Touihri
 */
public class ServiceChallenges implements IServiceChallenges{
 
    @Override
    public void AddChallenges(Challenges C) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean UpdateChallenges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DeleteChallenges() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ObservableList<Challenges> getChallengesList() {
        ObservableList<Challenges> ChallengesList= FXCollections.observableArrayList();
        Connection conn= Connexion.getInstance().getConnection();
        String query= "SELECT * FROM CHALLENGES";
    Statement st ;
    ResultSet rs ;

        try{
                st= conn.createStatement();
                rs = st.executeQuery(query);
                Challenges challenges ;
            while (rs.next()){
            challenges = new Challenges (rs.getInt("ID"),rs.getString("Titre"),rs.getString("Begin_Date"),rs.getString("End_Date"),rs.getString("Image"));
                ChallengesList.add(challenges);
            }
            }catch (SQLException ex){
                                            }
                        return ChallengesList;
    }
    
    
}


 
