package model;

public class Admin extends User {
    private static final long serialVersionUID = 1L;
    private String department;

    public Admin() {
        super();
        this.role = "ADMIN";
        this.department = "Academic Operations";
    }

    public Admin(int id, String name, String email, String password, String phone) {
        super(id, name, email, password, phone, "ADMIN");
        this.department = "Academic Operations";
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String getRoleDescription() {
        return "System Administrator [" + department + "]: Full Access to Course CRUD & Students";
    }
}