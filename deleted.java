//how delete the data

// retrive the data
import java.sql.*;

public class deleted{
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
        String query="DELETE FROM emp where id=4;";
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
            int rowsaffected=stmt.executeUpdate(query);
            if(rowsaffected>0){
                System.out.println("DELETION successful."+rowsaffected+"row(s)affected");
            }else{
                System.out.println(" DELETION failed");
            }


            stmt.close();
            con.close();
            System.out.println();
            System.out.println("connection close successfully");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}