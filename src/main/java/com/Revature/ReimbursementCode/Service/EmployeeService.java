package com.Revature.ReimbursementCode.Service;

import Model.Employee;
import com.Revature.ReimbursementCode.DAO.EmployeeDAO;
import com.Revature.ReimbursementCode.DAO.EmployeeDAO;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private final EmployeeDAO employeeDAO;
    List<Employee> employeeList;
    private Employee sessionEmployee = null;

    public EmployeeService(EmployeeDAO employeeDAO){this.employeeDAO = employeeDAO;}

    public Employee addEmployee(Employee employee){return employeeDAO.create(employee);}

    public Employee getEmployee(String userName){return null;}

    public void removeEmployee(String userName){}

    public List<Employee> getEmployeeNames(){return employeeDAO.findAllNames();}

    public List<Employee> getAllEmployees(){return employeeDAO.findAll();}

    public void login(String userName,String userPassword){
        sessionEmployee = employeeDAO.loginCheck(userName,userPassword);
    }

    public void logout(){
        sessionEmployee = null;
    }

    public Employee getSessionEmployee(){return sessionEmployee;}

    public boolean roleCheck(){
        if(sessionEmployee.getRole().equals("Employee")){
            return false;
        } else {return true;}
    }
}
