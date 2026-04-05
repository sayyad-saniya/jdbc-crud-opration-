import java.sql.*;
import java.util.Scanner;
public class batchproccessing {
    public static void main(String[] args) throws ClassNotFoundException,SQLException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection=DriverManager.getConnection(url,username,password);
            System.out.println("connection successful");
            connection.setAutoCommit(false);
            /*Statement statement=connection.createStatement();
            statement.addBatch("INSERT INTO emp(id,name,job_title,salary)VALUES(5,'hussain','hr',6500000.0)");
            statement.addBatch("INSERT INTO emp(id,name,job_title,salary)VALUES(6,'sadik','Drug inspector',8500000.0)");
            statement.addBatch("INSERT INTO emp(id,name,job_title,salary)VALUES(7,'maryam','doc',5500000.0)");

            int[] batchResult= statement.executeBatch();
            connection.commit();
            System.out.println("batch executed successfully");*/

            //using prepaid statement
            String query ="INSERT INTO emp(id,name,job_title,salary)VALUES(?,?,?,?)";

            PreparedStatement preparedStatement =connection.prepareStatement(query);
            Scanner scanner=new Scanner(System.in);
            while (true){
                System.out.println("id: ");
                int Id =scanner.nextInt();
                scanner.nextLine();
                System.out.println("Name: ");
                String name=scanner.nextLine();
                System.out.println("job_title: ");
                String job_title=scanner.nextLine();
                System.out.println("salary: ");
                double salary=scanner.nextDouble();
                preparedStatement.setInt(1,Id);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,job_title);
                preparedStatement.setDouble(4,salary);
                preparedStatement.addBatch();
                System.out.println("add more value Y/N");
                String decision =scanner.nextLine();
                if(decision.toUpperCase().equals("N")){
                    break;
                }
                int[] batchResult =preparedStatement.executeBatch();
                connection.commit();
                System.out.println("batch execute");





            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}