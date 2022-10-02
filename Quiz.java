package com.velocity.quiz;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Quiz {
	

    HashMap<Integer, String> questions = new HashMap<>();
    Map<Integer, ArrayList<String>> options = new HashMap<>();
    ArrayList<String> answers = new ArrayList<>();
    int correctAnswersCount = 0;
    String studentName = "";
    public void createConnectionAndSaveQuestions(String student) throws SQLException,NullPointerException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement saveStudent = null;
        final String query = "select * from paper";
        final String studentDetailsQuery = "select * from studentdetails";
        try {
            //step 1
           // Class.forName("com.mysql.jdbc.Driver");
            //step 2
           // con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "Ivmahajan@414");
            //Step 3
            con =DbConnection.getconnectionDeatalis();
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSet rs2 = stmt2.executeQuery(studentDetailsQuery);

            ps = con.prepareStatement("insert into paper(question,option1,option2,option3,option4,answer)values(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),"
                    + "(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?)");
            saveStudent = con.prepareStatement("insert into studentdetails(studentname, score)values(?,?)");

            if(!rs2.isBeforeFirst()) {
                //no student found starting the test
                System.out.println("Test Started");
            } else {
                while(rs2.next()) {
                    if(rs2.getString("studentname").equalsIgnoreCase(student)) {
                        System.out.println("You have already given the test");
                        System.out.println("Dear " + rs2.getString("studentname") + " Your score is " + rs2.getInt("score"));
                       // displayQuestionsAndCalculateMarks();
                        return;
                    
                    }
                }
            }

            if(!rs.isBeforeFirst()) {
                System.out.println("no data");

                //For Question 1
                ps.setString(1, "Who invented Java Programming?");
                ps.setString(2, "Guido van Rossum");
                ps.setString(3, "James Gosling");
                ps.setString(4, "Dennis Ritchie");
                ps.setString(5,"Bjarne Stroustrup");
                ps.setString(6, "James Gosling");

                //For Question 2
                ps.setString(7, "Which statement is true about Java?");
                ps.setString(8, "Java is a sequence-dependent programming language");
                ps.setString(9, "Java is a code dependent programming language");
                ps.setString(10, "Java is a platform-dependent programming language");
                ps.setString(11,"Java is a platform-independent programming language");
                ps.setString(12, "Java is a platform-independent programming language");

                //For Question 3
                ps.setString(13, "Which one of the following is not a Java feature?");
                ps.setString(14, "Object-oriented");
                ps.setString(15, "Use of pointers");
                ps.setString(16, "Portable");
                ps.setString(17,"Dynamic & Extensible");
                ps.setString(18, "Use of pointers");

                //For Question 4
                ps.setString(19, "What is the extension of java code files?");
                ps.setString(20, ".js");
                ps.setString(21, ".txt");
                ps.setString(22, ".class");
                ps.setString(23,".java");
                ps.setString(24, ".java");

                //For Question 5
                ps.setString(25, "Which of the following is not an OOPS concept in Java?");
                ps.setString(26, "Polymorphism");
                ps.setString(27, "Inheritance");
                ps.setString(28, "Compilation");
                ps.setString(29,"Encapsulation");
                ps.setString(30, "Compilation");

                //For Question 6
                ps.setString(31, "Which of the following is a superclass of every class in Java?");
                ps.setString(32, "ArrayList");
                ps.setString(33, "Abstract class");
                ps.setString(34, "Object Class");
                ps.setString(35,"String");
                ps.setString(36, "Object Class");

                //For Question 7
                ps.setString(37, "Which of these keywords are used for the block to be examined for exceptions?");
                ps.setString(38, "check");
                ps.setString(39, "throw");
                ps.setString(40, "catch");
                ps.setString(41,"try");
                ps.setString(42, "try");

                //For Question 8
                ps.setString(43, "Which one of the following is not an access modifier?");
                ps.setString(44, "Protected");
                ps.setString(45, "Void");
                ps.setString(46, "Public");
                ps.setString(47,"Private");
                ps.setString(48, "Void");

                //For Question 9
                ps.setString(49, "What is the return type of the hashCode() method in the Object class?");
                ps.setString(50, "Object");
                ps.setString(51, "int");
                ps.setString(52, "long");
                ps.setString(53,"void");
                ps.setString(54, "int");

                //For Question 10
                ps.setString(55, "An interface with no fields or methods is known as a __.?");
                ps.setString(56, "Runnable Interface");
                ps.setString(57, "Marker Interface");
                ps.setString(58, "Absract Interface");
                ps.setString(59,"CharSequence Interface");
                ps.setString(60, "Marker Interface");

                //step 4
                ps.executeUpdate();
                
            } else {
                System.out.println("Test started");
                //Fetch data from database and iterate over it store into collections
                while(rs.next()) {
                    //first populate the questions in hashmap.
                    questions.put(rs.getInt("qid"), rs.getString("question"));
                    //second populate four option in second hashmap.
                    options.put(rs.getInt("qid"), new ArrayList<String>());
                    options.get(rs.getInt("qid")).add(rs.getString("option1"));
                    options.get(rs.getInt("qid")).add(rs.getString("option2"));
                    options.get(rs.getInt("qid")).add(rs.getString("option3"));
                    options.get(rs.getInt("qid")).add(rs.getString("option4"));
                    answers.add(rs.getString("answer"));
                }
                displayQuestionsAndCalculateMarks();
                saveStudent.setString(1, studentName);
                saveStudent.setInt(2, correctAnswersCount);
                saveStudent.executeUpdate();
                
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("You want see all students list reply Yes/No");
                Scanner sc = new Scanner(System.in);
                String response = sc.next();
                if (response.equalsIgnoreCase("Yes")) {
                StudentRecords.displayListOfStudent();
                }else if(response.equalsIgnoreCase("No")) {
                	System.out.println("You want to see only your score reply Yes/No");
                	Scanner sc1 = new Scanner(System.in);
                    String response1 = sc1.next();
                    if(response1.equalsIgnoreCase("Yes")) {
                    StudentRecords.displaySpecificStudent();
                    }else {
                    	System.out.println("Thank you");
                    }
                    
                }
             
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            con.close();
            ps.close();
        }
    }



	public void displayQuestionsAndCalculateMarks() {
        List keys = new ArrayList(questions.keySet());
        Collections.shuffle(keys);
        int quesNumber = 1;
       // for(Map.Entry<Integer, String> entries : questions.entrySet()) {}
        for (Object o : keys) {   //itrating qus 1 by 1 
            System.out.println("Que No." +  quesNumber + " " + questions.get(o));
            List<String> str = options.get(o);  //[Guido van Rossum, James Gosling, Dennis Ritchie, Bjarne Stroustrup]
            System.out.println("1." + " " + str.get(0));
            System.out.println("2." + " " + str.get(1));
            System.out.println("3." + " " + str.get(2));
            System.out.println("4." + " " + str.get(3));

            System.out.println("------------------------------------------------");

            Scanner sc = new Scanner(System.in);
            System.out.print("Answer : ");

            Integer answer = sc.nextInt();
            
            if(answers.contains(str.get(answer-1))) {
                correctAnswersCount++;
                System.out.println("Answer is Correct");
            } else {
                System.out.println("Answer is wrong");
            }
            
            quesNumber++;
        }
        System.out.println("Test completed successfully, total correct answers are : " + correctAnswersCount);
        if(correctAnswersCount > 8) {
            System.out.println("Your Grade is A");
        } else if(correctAnswersCount <= 8 && correctAnswersCount >= 6) {
            System.out.println("Your Grade is B");
        } else if(correctAnswersCount == 5) {
            System.out.println("Your Grade is C");
        } else {
            System.out.println("Your Grade is D and you are fail");
        }
      
    }



    public void takeStudentDetails() throws SQLException {
        System.out.println("Please enter your name");
        Scanner sc = new Scanner(System.in);
        studentName = sc.nextLine();
        createConnectionAndSaveQuestions(studentName);
    }

    public static void main(String args[]) throws SQLException,NullPointerException {
        Quiz qz = new Quiz();
        qz.takeStudentDetails();
    }

}
