package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.UserDAO;
import model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        if (name == null || email == null || password == null || phone == null) {
            request.setAttribute("errorMessage", "All registration fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (userDAO.isEmailRegistered(email.trim())) {
            request.setAttribute("errorMessage", "Email already in use. Please log in.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User newUser = new User(name.trim(), email.trim(), password, phone.trim(), role != null ? role : "STUDENT");
        if (userDAO.registerUser(newUser)) {
            request.setAttribute("successMessage", "Registration complete! Please sign in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Registration failed due to a database error.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}