import java.sql.*;

public class jdbcconnection  {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mca_lab_test", "root", "A7uca080"
            );
            // Create statement
            Statement statement = connection.createStatement();
            // Execute query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client_master");
            // Process results
            while (resultSet.next()) {
                int code = resultSet.getInt("client_no");
                String title = resultSet.getString("name").trim();
                System.out.println("Code: " + code + " | Title: " + title);
            }
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}