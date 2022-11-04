import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = ""),
                @WebInitParam(name = "password", value = "")
        }
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //get request parameters for userid and Password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        //if result is true and password matches with the input password then login successful
        if (user.matches("^[A-Z]{1}[a-z]{2,}$") & pwd.matches("^(?=.*[A-z])(?=.*[0-9])[-+_!@#$%^&*.,?0-9A-z]{8,}$")) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color = red> Either user name or password is wrong.</font>");
            requestDispatcher.include(request, response);
        }
    }

}



