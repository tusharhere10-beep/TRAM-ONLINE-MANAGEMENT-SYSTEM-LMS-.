package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import util.DBConnection;

public class CourseDAO {
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT id, course_name, description, instructor, duration, category FROM courses ORDER BY id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Course(rs.getInt("id"), rs.getString("course_name"), rs.getString("description"), rs.getString("instructor"), rs.getString("duration"), rs.getString("category")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Course getCourseById(int id) {
        String sql = "SELECT id, course_name, description, instructor, duration, category FROM courses WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Course(rs.getInt("id"), rs.getString("course_name"), rs.getString("description"), rs.getString("instructor"), rs.getString("duration"), rs.getString("category"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean addCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, description, instructor, duration, category) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getInstructor());
            ps.setString(4, course.getDuration());
            ps.setString(5, course.getCategory());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name=?, description=?, instructor=?, duration=?, category=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getInstructor());
            ps.setString(4, course.getDuration());
            ps.setString(5, course.getCategory());
            ps.setInt(6, course.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public int getTotalCoursesCount() {
        String sql = "SELECT COUNT(*) FROM courses";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}