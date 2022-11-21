package Model;

public enum Identity {
    Employee("Employee"), Manager("Manager");

    String role;
    Identity(String role){
        this.role = role;
    }
}
