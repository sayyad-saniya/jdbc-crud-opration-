// retrive using prepared statement

import java.sql.*;

public class nnn{
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
        String query ="select * from emp where name=? AND job_title=?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("loaded");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con =DriverManager.getConnection(url,username,password);
            System.out.println("successfully");
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,"affan");
            preparedStatement.setString(2,"full stack dev");
            ResultSet result= preparedStatement.executeQuery();
            while (result.next()){
                int id=result.getInt("id");
                String name=result.getString("name");
                String job_title =result.getString("job_title");
                double salary=result.getDouble("salary");
                System.out.println("ID:"+id);
                System.out.println("NAME:"+name);
                System.out.println("JOB TITLE:"+job_title);
                System.out.println("SALARY:"+salary);

            }
            result.close();
             preparedStatement.close();
            con.close();
            System.out.println();
            System.out.println("closed");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}