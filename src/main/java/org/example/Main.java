package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "8400";

    public static void main(String[] args) {
        // not needed in jdbc 4+
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("class founded");
//
//        }catch (ClassNotFoundException e){
//            System.out.println(e.getMessage());
//            System.out.println("class not found");
//
//        }
        // (try with resources) automatically closes the connection with database
        /*try(Connection conn = DriverManager.getConnection(URL,USER, PASSWORD))
        {
            //System.out.println("database connection done");
            Statement statement =conn.createStatement();
            //String query = "select * from students";
            //String query = String.format("INSERT INTO students(name ,age, marks) values( '%s', %o, %f)","aman",23,88.8);
                        //executeQuery is only use to show table
            //ResultSet resultSet= statement.executeQuery(query);
            //String query = String.format("UPDATE students SET marks = %f WHERE id = %d", 78.6,2);
            String query = String.format("DELETE FROM students WHERE id=3");
            int rowsaffected= statement.executeUpdate(query);
            /*
            this loop is used to print data
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double marks = resultSet.getDouble("marks");
                int age = resultSet.getInt("age");
                System.out.println("ID :" +id);
                System.out.println("name :" +name);
                System.out.println("name :" +age);
                System.out.println("marks :" +marks);

            }*/
            /*if (rowsaffected>0){
                System.out.println("rows are affected");
            }else {
                System.out.println("issues in data rows not affected");
            }

    } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


//                  using prepared statements doing operations of insert
/*
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO students(name ,age, marks) values(? ,? ,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,"akash");
            preparedStatement.setInt(2,21);
            preparedStatement.setDouble(3,99.9);

            int rowsaffected = preparedStatement.executeUpdate();


            if (rowsaffected > 0) {
                System.out.println("rows are affected");
            } else {
                System.out.println("issues in data rows not affected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        // using prepared statements doing operations of retrive data or read data

        /*try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT marks FROM students WHERE id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                System.out.println("marks: "+ resultSet.getDouble("marks"));
            }else{
                System.out.println("marks not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

//                  batch processing using Statement interface

//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//
//            Statement statement = conn.createStatement();
//            Scanner scanner=new Scanner(System.in);
//
//            while (true) {
//                System.out.print("Enter name: ");
//                String name = scanner.next();
//
//                System.out.print("Enter age: ");
//                int age = scanner.nextInt();
//
//                System.out.print("Enter marks: ");
//                double marks = scanner.nextDouble();
//
//
//                System.out.print("Enter more data (Y/N): ");
//                String choice = scanner.next();
//                String query = String.format(
//                        "INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)",
//                        name, age, marks
//                );
//                statement.addBatch(query);
//
//                if (choice.toUpperCase().equals("N")) {
//                    break;
//                }
//            }
//
//            int [] arr =statement.executeBatch();
//            for(int i=0; i <arr.length; i++){
//                if (arr[i]==0){
//                    System.out.println("Query " + i+ "not executed");
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        // batch processing using prepared statement

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = "INSERT INTO students(name, age, marks) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter name: ");
                String name = scanner.next();

                System.out.print("Enter age: ");
                int age = scanner.nextInt();

                System.out.print("Enter marks: ");
                double marks = scanner.nextDouble();

                // 🔹 Set values
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setDouble(3, marks);

                // 🔹 Add to batch
                ps.addBatch();

                System.out.print("Enter more data (Y/N): ");
                String choice = scanner.next();

                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }

            // 🔹 Execute batch
            int[] arr = ps.executeBatch();

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    System.out.println("Query " + i + " not executed");
                } else {
                    System.out.println("Query " + i + " executed successfully");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}


















/*
Connection conn=null;
        try {
            conn = DriverManager.getConnection(URL ,USER ,PASSWORD);
            System.out.println("database connection done");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
                System.out.println("connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
 */
