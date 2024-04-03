package Jdbc.Database;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employeee {
    Connection connection;

    public void ConnectDB() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "DATABASE";
        String password = "6246";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int UpdateDB(String sql, Object[] params) {
        int result = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] arg) {
        Employeee tx = new Employeee();
        Scanner scan = new Scanner(System.in);
        tx.ConnectDB();

        System.out.print("Enter the employee ID : ");
        int employeeid = scan.nextInt();
        System.out.print("Enter the billing rate : ");
        int billingrate = scan.nextInt();

        String sql = "INSERT INTO EMPLOYEES (employeeid, billingrate) VALUES (?, ?)";
        Object[] params = {employeeid, billingrate};
        int rowsInserted = tx.UpdateDB(sql, params);
        if (rowsInserted > 0)
            System.out.println("Employee was inserted successfully!");
    }
}