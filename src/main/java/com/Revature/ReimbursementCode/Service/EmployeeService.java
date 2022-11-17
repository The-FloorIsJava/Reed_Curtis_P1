package com.Revature.ReimbursementCode.Service;

import Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    List<Employee> employeeList;


    public EmployeeService(){employeeList = new ArrayList<>();}


    public void addEmployee(String role, String username, String user_password){
        Employee newEmployee = new Employee(role,username,user_password);
        employeeList.add(newEmployee);
    }
    public void addEmployee(Employee employee){employeeList.add(employee);}
    public List<Employee> getAllEmployees(){return employeeList;}
}
