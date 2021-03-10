/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignite.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import com.ignite.utils.DataBase;

/**
 *
 * @author mouhamed
 */
public class ServiceStat {
 
    public Map<String,Integer> getStat() {
        Map<String,Integer> map = new HashMap<String, Integer>();
    try {
            
            String query = "select user ,count(id) as nbr from commande GROUP by id ORDER by nbr desc";
            Statement st=  DataBase.getInstance().getConnection().createStatement();
           ResultSet rest = st.executeQuery(query);
            System.out.println("Affichage Done");

            while (rest.next()) {
                map.put(rest.getString("user"),rest.getInt("nbr"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return map ;
    }

}