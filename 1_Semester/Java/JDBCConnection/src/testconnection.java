import java.sql.*;

public class testconnection {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mca_lab_test", "root",
                "A7uca080")) {

            System.out.println("✔ Connection Successful!");
            System.out.println("Database Product: " + con.getMetaData().getDatabaseProductName());
            System.out.println("Version: " + con.getMetaData().getDatabaseProductVersion());
            con.close();
        } catch (SQLException e) {
            System.out.println("✘ Database Connection Failed!");
            System.out.println("Reason: " + e.getMessage());
        }

    }
}
