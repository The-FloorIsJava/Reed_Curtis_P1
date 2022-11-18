package com.Revature.ReimbursementCode.Service;

import Model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    List<Employee> employeeList;


    public EmployeeService(){employeeList = new ArrayList<>();}


    public void addEmployee(String role, String userName, String userPassword){
        Employee newEmployee = new Employee(role,userName,userPassword);
        employeeList.add(newEmployee);
    }
    public void addEmployee(Employee employee){employeeList.add(employee);}

    public Employee getEmployee(String userName){
        for(int i = 0;i<employeeList.size();i++){
            Employee e = employeeList.get(i);
            if(e.getUserName().equals(userName)){return employeeList.get(i);}
        } return null;
    }

    public void removeEmployee(String userName){
        for(int i = 0;i<employeeList.size();i++){
            Employee e = employeeList.get(i);
            if(e.getUserName().equals(userName)){employeeList.remove(i);}
        }
    }

    public List<Employee> getAllEmployees(){return employeeList;}

}
