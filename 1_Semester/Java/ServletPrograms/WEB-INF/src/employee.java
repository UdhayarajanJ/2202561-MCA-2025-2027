import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;

@WebServlet("/employee")
public class employee extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String salary = request.getParameter("salary");

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database (change db name/user/password)
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dbms_2202541",
                    "root",
                    "A7uca080");

            // Insert Query
            String sql = "INSERT INTO employee(name, email, salary) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, salary);

            ps.executeUpdate();

            // Show success message
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Employee Registered Successfully!</h2>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
