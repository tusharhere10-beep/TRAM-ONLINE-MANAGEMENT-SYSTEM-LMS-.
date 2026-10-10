package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Enrollment;
import util.DBConnection;

public class EnrollmentDAO {
    public boolean isAlreadyEnrolled(int studentId, int courseId) {
        String sql = "SELECT id FROM enrollments WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) { return false; }
    }

    public boolean enrollStudent(int studentId, int courseId) {
        if (isAlreadyEnrolled(studentId, courseId)) return false;
        String sql = "INSERT INTO enrollments (student_id, course_id, progress) VALUES (?, ?, 0)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.id, e.student_id, e.course_id, e.enrollment_date, e.progress, c.course_name, c.instructor, c.category FROM enrollments e JOIN courses c ON e.course_id = c.id WHERE e.student_id = ? ORDER BY e.enrollment_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Enrollment en = new Enrollment(rs.getInt("id"), rs.getInt("student_id"), rs.getInt("course_id"), rs.getTimestamp("enrollment_date"), rs.getInt("progress"));
                    en.setCourseName(rs.getString("course_name"));
                    en.setInstructor(rs.getString("instructor"));
                    en.setCategory(rs.getString("category"));
                    list.add(en);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean updateProgress(int studentId, int courseId, int newProgress) {
        if (newProgress < 0) newProgress = 0;
        if (newProgress > 100) newProgress = 100;
        String sql = "UPDATE enrollments SET progress = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newProgress);
            ps.setInt(2, studentId);
            ps.setInt(3, courseId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public int getTotalEnrollmentsCount() {
        String sql = "SELECT COUNT(*) FROM enrollments";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}