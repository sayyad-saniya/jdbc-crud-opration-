// insert using prepared statement

import java.sql.*;
import java.util.Scanner;

public class preparedstatement_insert{
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
        String query ="INSERT INTO emp(id,name,job_title,salary)VALUES(?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("loaded");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con =DriverManager.getConnection(url,username,password);
            System.out.println("successfully");
            Scanner scanner= new Scanner(System.in);
            int id =scanner.nextInt();
            scanner.nextLine();
            String name=scanner.nextLine();
            String job_title= scanner.nextLine();
            Double salary= scanner.nextDouble();
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,job_title);
            preparedStatement.setDouble(4,salary);

           int rowsAffected= preparedStatement.executeUpdate();
           if(rowsAffected>0){
               System.out.println("insert");
           }else {
               System.out.println("failed insert");
           }
            preparedStatement.close();
            con.close();
            System.out.println();
            System.out.println("closed");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}