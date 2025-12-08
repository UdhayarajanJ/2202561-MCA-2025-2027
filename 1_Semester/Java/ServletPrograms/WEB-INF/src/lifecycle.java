import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/lifecycle")
public class lifecycle extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet Initialized (init method called)");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h3>Servlet Life Cycle Demo</h3>");
        out.println("<p>Request Processed (doGet method called)</p>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet Destroyed (destroy method called)");
    }
}

// javac -cp "C:\tomcat\lib\servlet-api.jar" lifecycle.java
