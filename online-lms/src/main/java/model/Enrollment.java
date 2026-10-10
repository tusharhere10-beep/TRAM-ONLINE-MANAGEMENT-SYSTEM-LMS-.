package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int studentId;
    private int courseId;
    private Timestamp enrollmentDate;
    private int progress; // 0 - 100

    private String studentName;
    private String studentEmail;
    private String courseName;
    private String instructor;
    private String category;

    public Enrollment() {}

    public Enrollment(int id, int studentId, int courseId, Timestamp enrollmentDate, int progress) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.progress = progress;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int id) { this.studentId = id; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int id) { this.courseId = id; }
    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }
    public Timestamp getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Timestamp date) { this.enrollmentDate = date; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String n) { this.studentName = n; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String n) { this.courseName = n; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String i) { this.instructor = i; }
    public String getCategory() { return category; }
    public void setCategory(String c) { this.category = c; }
}