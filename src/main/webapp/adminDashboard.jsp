<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Course, dao.CourseDAO, dao.UserDAO, dao.EnrollmentDAO, java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
    CourseDAO cDao = new CourseDAO();
    UserDAO uDao = new UserDAO();
    EnrollmentDAO eDao = new EnrollmentDAO();
%>
<!DOCTYPE html>
<html>
<head><title>Admin Control Console</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <div class="container">
        <h1>Admin Console</h1>
        <div class="stats-grid">
            <div class="stat-card">Students: <%= uDao.getTotalStudentsCount() %></div>
            <div class="stat-card">Courses: <%= cDao.getTotalCoursesCount() %></div>
            <div class="stat-card">Enrollments: <%= eDao.getTotalEnrollmentsCount() %></div>
        </div>
    </div>
</body>
</html>