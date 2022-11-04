import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = ""),
                @WebInitParam(name = "password", value = "123")
        }
)

public class LoginServlet extends HttpServlet {
    String validateName = "[A-Z][a-z]{2,}";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //get request parameters for userid and Password
        String user = request.getParameter("user");
        // defining pattern to validate the user name with first letter capital and three minimum characters
        boolean result = Pattern.compile(validateName).matcher(user).matches();
        String pwd = request.getParameter("pwd");
        //get servlet configure init params
        String password = getServletConfig().getInitParameter("password");
        //if result is true and password matches with the input password then login successful
        if (result && password.equals(pwd)) {
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



