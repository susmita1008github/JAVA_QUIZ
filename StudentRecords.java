package com.velocity.quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentRecords {

	
	public static void displayListOfStudent() {
		
		 
        System.out.println("List Of Students");
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Student ID"+"|" + "Student Name"+"|" + "Student Score");
        Connection con = null;
		Statement stmt2;
		try {
			final String studentDetailsQuery = "select * from studentdetails";
			con =DbConnection.getconnectionDeatalis();
			stmt2 = con.createStatement();
		    ResultSet rs3 = stmt2.executeQuery(studentDetailsQuery);
		    
			while (rs3.next()) {
				int id = rs3.getInt("sid");
				String name = rs3.getString("studentname");
				int score = rs3.getInt("score");
				System.out.println(id + "|" + name + "|" + score);
			}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		
	}
	
	public static void displaySpecificStudent() {
		System.out.println("---------------------------------------------------------------------");
		 Connection con = null;
		 Statement stmt3;
		 try {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter your Id");		 
         int id = sc.nextInt();
		final String studentDetailsQuery = "select * from studentdetails where sid="+id;
		con =DbConnection.getconnectionDeatalis();
		
			stmt3 = con.createStatement();
			ResultSet rs4 = stmt3.executeQuery(studentDetailsQuery);
			
			while(rs4.next()) {
				
				int id1 = rs4.getInt("sid");
				String name = rs4.getString("studentname");
				int score = rs4.getInt("score");
				System.out.println("Student ID"+"|" + "Student Name"+"|" + "Student Score");
				System.out.println(id1 + "|" + name + "|" + score);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
