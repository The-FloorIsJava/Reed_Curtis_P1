package Model;

public class Employee {
    private String role;
    private String username;
    private String user_password;

public Employee (String role, String username, String user_password) {
    this.role = role;
    this.username = username;
    this.user_password = user_password;
}
    public String getRole() {return this.role;}

    public String getUsername(){return this.username;}

    public String getUser_password(){return this.user_password;}

}
