package model;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String courseName;
    private String description;
    private String instructor;
    private String duration;
    private String category;

    public Course() {}

    public Course(int id, String courseName, String description, String instructor, String duration, String category) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.duration = duration;
        this.category = category;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String name) { this.courseName = name; }
    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String inst) { this.instructor = inst; }
    public String getDuration() { return duration; }
    public void setDuration(String dur) { this.duration = dur; }
    public String getCategory() { return category; }
    public void setCategory(String cat) { this.category = cat; }
}