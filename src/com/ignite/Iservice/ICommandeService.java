
package com.ignite.Iservice;

import com.ignite.entity.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mouhamed
 */
public interface  ICommandeService <T> {
           void AjoutCommande(T t) throws SQLException;
    boolean deleteCommande(int  id) throws SQLException;
    boolean updateCommande(int id) throws SQLException;
    List<T> readAllCommande() throws SQLException;
    void AjoutCommandeproduit(int c,int p) throws SQLException;
    public void valide_btn(Commande a)  throws SQLException;
    
}
