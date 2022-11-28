package Controller;

import Model.Employee;
import com.Revature.ReimbursementCode.DAO.EmployeeDAO;
import com.Revature.ReimbursementCode.Service.EmployeeService;
import com.Revature.ReimbursementCode.UTIL.DTO.LoginCred;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {
    EmployeeService employeeService;

    Javalin app;
    public EmployeeController(Javalin app,EmployeeService employeeService){
        this.employeeService = employeeService;
        this.app = app;
    }

    public void employeeEndpoint(){
        app.post("employee", this::postEmployeeHandler);
        app.get("employee", this::getAllEmployeesHandler);
        app.get("employee/{userName}",this::getSpecifiedEmployeeHandler);
        app.post("login",this::loginHandler);
        app.delete("logout",this::logoutHandler);
    }

    private void logoutHandler(Context context) {
        String userName = employeeService.getSessionEmployee().getUserName();
        employeeService.logout();
        context.json(userName + " is now logged out.");
    }

    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        LoginCred loginCred = mapper.readValue(context.body(), LoginCred.class);
        employeeService.login(loginCred.getUserName(), loginCred.getPassword());
        context.json(loginCred.getUserName() + " is now logged in.");
    }


    private void getSpecifiedEmployeeHandler(Context context) {
        String userName = context.pathParam("userName");
        Employee employee = employeeService.getEmployee(userName);
        context.json(employee);
    }

    private void getAllEmployeesHandler(Context context) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        context.json(allEmployees);
    }

    private void postEmployeeHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = mapper.readValue(context.body(),Employee.class);
        employeeService.addEmployee(employee);
        context.json(employee);
    }
}
