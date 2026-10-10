<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Enrollment, dao.EnrollmentDAO, java.util.List" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect("login.jsp"); return; }
    EnrollmentDAO dao = new EnrollmentDAO();
    List<Enrollment> enrollments = dao.getEnrollmentsByStudent(user.getId());
%>
<!DOCTYPE html>
<html>
<head><title>Student Dashboard</title><link rel="stylesheet" href="css/style.css"></head>
<body>
    <nav class="navbar">
        <a href="studentDashboard.jsp">ApexLMS</a>
        <span>Welcome <%= user.getName() %></span>
        <a href="logout">Sign Out</a>
    </nav>
    <div class="container">
        <h1>Welcome back, <%= user.getName() %>!</h1>
        <div class="course-grid">
            <% for (Enrollment e : enrollments) { %>
                <div class="course-card">
                    <h3><%= e.getCourseName() %></h3>
                    <p>Progress: <%= e.getProgress() %>%</p>
                    <a href="course-details?id=<%= e.getCourseId() %>">Continue</a>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>