package com.sap.assessment.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseClass {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL2 = "jdbc:mysql://localhost:3306/parking_lot";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "amrutha";
    
    public Connection getConnection(){
	
    	Connection conn = null;
    	try{
    	 Class.forName(JDBC_DRIVER);
        conn = (Connection) DriverManager.getConnection(DB_URL2,USER,PASS);
    	}
    	catch(ClassNotFoundException cnf){
    		cnf.printStackTrace();
    	}
    	catch(SQLException sql){
    		sql.printStackTrace();
    	}
         return conn;
}
}