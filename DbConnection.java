package com.velocity.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	 static Connection con = null;

	public static Connection getconnectionDeatalis() {
		try {
		 Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "Ivmahajan@414");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
		
	}
	
	 
	

}
