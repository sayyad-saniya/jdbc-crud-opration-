
// retrive the data
import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
        String query="Select * from emp;";
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("loaded");
        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("loaded connection");
            //in this connection is interface and con is instance
            //drivermanager class have getconnection
            // method it requred url, user name ,passward and establish connection
            // we store in connection con
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String job_title=rs.getString("job_title");
                Double salary=rs.getDouble("salary");
                System.out.println();
                System.out.println("=================");
                System.out.println("ID:"+id);
                System.out.println("Name"+name);
                System.out.println("job title"+job_title);
                System.out.println("Salary"+salary);




            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println();
            System.out.println("connection close successfully");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}