import java.sql.*;
import java.util.Scanner;

public class clientcrudoperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mca_lab_test", "root",
                    "A7uca080");

            while (true) {
                System.out.println("\n--- CLIENT MASTER CRUD MENU ---");
                System.out.println("1. Insert");
                System.out.println("2. Display All");
                System.out.println("3. Update Name");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                switch (choice) {

                    case 1:
                        insertClient(con, sc);
                        break;

                    case 2:
                        displayClients(con);
                        break;

                    case 3:
                        updateClient(con, sc);
                        break;

                    case 4:
                        deleteClient(con, sc);
                        break;

                    case 5:
                        con.close();
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void insertClient(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter client_no: ");
        String cno = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter city: ");
        String city = sc.nextLine();

        String sql = "INSERT INTO client_master (client_no, name, city) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cno);
        ps.setString(2, name);
        ps.setString(3, city);

        int rows = ps.executeUpdate();
        System.out.println("Inserted " + rows + " record(s)");
    }

    static void displayClients(Connection con) throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM client_master");

        System.out.println("\n--- CLIENT TABLE ---");
        while (rs.next()) {
            System.out.println(rs.getString("client_no") + " | "
                    + rs.getString("name") + " | "
                    + rs.getString("city"));
        }
    }

    static void updateClient(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter client_no to update: ");
        String cno = sc.nextLine();
        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        String sql = "UPDATE client_master SET name=? WHERE client_no=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, newName);
        ps.setString(2, cno);

        int rows = ps.executeUpdate();
        System.out.println("Updated " + rows + " record(s)");
    }

    static void deleteClient(Connection con, Scanner sc) throws Exception {
        System.out.print("Enter client_no to delete: ");
        String cno = sc.nextLine();

        String sql = "DELETE FROM client_master WHERE client_no=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cno);

        int rows = ps.executeUpdate();
        System.out.println("Deleted " + rows + " record(s)");
    }
}
