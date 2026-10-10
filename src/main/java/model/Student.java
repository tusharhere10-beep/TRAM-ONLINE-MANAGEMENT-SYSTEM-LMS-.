package model;

public class Student extends User {
    private static final long serialVersionUID = 1L;
    private int enrolledCoursesCount;

    public Student() {
        super();
        this.role = "STUDENT";
    }

    public Student(int id, String name, String email, String password, String phone) {
        super(id, name, email, password, phone, "STUDENT");
        this.enrolledCoursesCount = 0;
    }

    public int getEnrolledCoursesCount() { return enrolledCoursesCount; }
    public void setEnrolledCoursesCount(int count) { this.enrolledCoursesCount = count; }

    @Override
    public String getRoleDescription() {
        return "Student Learner: " + this.name + " (" + enrolledCoursesCount + " enrolled courses)";
    }
}