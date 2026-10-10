package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.EnrollmentDAO;
import model.User;

@WebServlet({"/enroll", "/my-courses"})
public class EnrollmentServlet extends HttpServlet {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp?message=Please+sign+in+to+enroll.");
            return;
        }

        User user = (User) session.getAttribute("user");
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        if (enrollmentDAO.isAlreadyEnrolled(user.getId(), courseId)) {
            response.sendRedirect("course-details?id=" + courseId + "&info=Already+enrolled.");
            return;
        }

        enrollmentDAO.enrollStudent(user.getId(), courseId);
        response.sendRedirect("my-courses?success=Successfully+enrolled!");
    }
}