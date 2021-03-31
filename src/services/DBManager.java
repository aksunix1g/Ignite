/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charf
 */
public class DBManager {
    private static DBManager instance ;
	private Connection cnx ;
	
	private final String URL="jdbc:mysql://127.0.0.1:3306/dbprod";
	private final String USER="root" ;
	private final String PASSWORD="";
	
	
	public DBManager() {
		try {
			cnx = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Connecting to db ..");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static DBManager getInstance() {
		if(instance==null) {
			instance=new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return cnx;
	}
    
}
