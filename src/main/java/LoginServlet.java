import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "Shalesh"),
                @WebInitParam(name = "password", value = "123")
        }
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //get request parameters for userId and Password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        //get servlet config init params
        String userId = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        if (userId.equals(user) && password.equals(pwd)) {
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



