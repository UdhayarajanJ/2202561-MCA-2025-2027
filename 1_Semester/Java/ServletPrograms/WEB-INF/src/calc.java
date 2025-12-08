import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/calc")
public class calc extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int a = Integer.parseInt(request.getParameter("num1"));
        int b = Integer.parseInt(request.getParameter("num2"));

        int add = a + b;
        int mul = a * b;

        request.setAttribute("add", add);
        request.setAttribute("mul", mul);

        // Forward back to SAME JSP
        request.getRequestDispatcher("calc.jsp").forward(request, response);
    }
}
