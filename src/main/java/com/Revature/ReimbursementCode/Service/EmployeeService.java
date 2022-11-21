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

    public void removeEmployee(String userName){

    }

    public List<Employee> getAllEmployees(){return employeeDAO.findAll();}

    public void login(String userName,String userPassword){
        sessionEmployee = employeeDAO.loginCheck(userName,userPassword);
    }

    public void logout(){
        sessionEmployee = null;
    }

    public Employee getSessionEmployee(){return sessionEmployee;}
    /*
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

    public void login(String userName, String userPassword){
        sessionEmployee = EmployeeDAO.loginCheck(userName,userPassword);
    }

    public void logout(){sessionEmployee = null;}

     */
}
