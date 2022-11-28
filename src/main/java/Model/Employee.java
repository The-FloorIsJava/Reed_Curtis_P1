package Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    private int userId;
    private String role;
    private String userName;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String userPassword;

public Employee (int userId, String userName, String userPassword,String role) {
    this.userId = userId;
    this.role = role;
    this.userName = userName;
    this.userPassword = userPassword;
}

    public Employee() {

    }

    public void setRole(String role) {this.role = role;}

    public void setUserName(String userName) {this.userName = userName;}

    public void setUserPassword(String userPassword){this.userPassword = userPassword;}

    public String getRole() {return this.role;}

    public String getUserName(){return this.userName;}

    public String getUserPassword(){return this.userPassword;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getUserId() {return userId;}

}
