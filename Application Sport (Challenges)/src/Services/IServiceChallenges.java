/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Challenges;
import javafx.collections.ObservableList;


/**
 *
 * @author Touihri
 */
public interface IServiceChallenges {
    public void  AddChallenges(Challenges C);
    public boolean  UpdateChallenges();
    public boolean  DeleteChallenges();
    public ObservableList<Challenges> getChallengesList();
   
    
}
