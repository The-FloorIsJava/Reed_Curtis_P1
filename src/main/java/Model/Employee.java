package Model;

public class Employee {
    private String role;
    private String userName;
    private String userPassword;

public Employee (String role, String userName, String userPassword) {
    this.role = role;
    this.userName = userName;
    this.userPassword = userPassword;
}
    public String getRole() {return this.role;}

    public String getUserName(){return this.userName;}

    public String getUserPassword(){return this.userPassword;}

}
