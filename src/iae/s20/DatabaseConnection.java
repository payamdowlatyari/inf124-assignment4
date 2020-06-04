package iae.s20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect()  throws SQLException, ClassNotFoundException {
       
        String username = "sanjith";
        String password = "sanjith";

         String url = "jdbc:postgresql://localhost/ssdb";
         
//         try{
//        		Class.forName("com.mysql.jdbc.Driver");
//        	} catch (Exception e){
//        		System.out.println(e.toString());
//        	}

        	try{
        	// DB connection code
        	} catch (Exception e){
        		System.out.println(e.toString());
        	}
        
        System.out.println("DB connection established");

        return DriverManager.getConnection(url, username, password);
    }

	public static void disconnect() {
		DatabaseConnection.disconnect();
        System.out.println("DB connection closed");

		
	}
}
