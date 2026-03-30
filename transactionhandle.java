import java.sql.*;

public class transactionhandle {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";

        String withdrawQuery =
                "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";

        String depositQuery =
                "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        // load driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Loaded ");

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully ");

            con.setAutoCommit(false); // start transaction

            try {
                PreparedStatement withdrawStatement =
                        con.prepareStatement(withdrawQuery);

                PreparedStatement depositStatement =
                        con.prepareStatement(depositQuery);

                // withdraw from account123
                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account123");

                // deposit into account234
                depositStatement.setDouble(1, 500.00);
                depositStatement.setString(2, "account234");

                withdrawStatement.executeUpdate();
                depositStatement.executeUpdate();

                con.commit(); // save changes
                System.out.println("Transaction successful ");

            } catch (SQLException e) {

                con.rollback(); // undo changes if error
                System.out.println("Transaction failed ");
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}