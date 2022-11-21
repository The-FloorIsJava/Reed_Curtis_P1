package Controller;

import Model.Employee;
import com.Revature.ReimbursementCode.DAO.EmployeeDAO;
import com.Revature.ReimbursementCode.Service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class EmployeeController {
    EmployeeService employeeService;

    Javalin app;
    public EmployeeController(Javalin app){employeeService = new EmployeeService(new EmployeeDAO());}

    public void startAPI(){
        Javalin app = Javalin.create().start(8080);
        app.post("employee", this::postEmployeeHandler);
        app.get("employee", this::getAllEmployeesHandler);
        app.get("employee/{userName}",this::getSpecifiedEmployeeHandler);
        app.post("login",this::loginHandler);
        app.delete("logout",this::logoutHandler);
    }

    private void logoutHandler(Context context) {
    }
/*
    private void loginHandler(Context context) {
        ObjectMapper mapper = new ObjectMapper();
        LoginCred loginCred = mapper.readValue(context.body(), LoginCred.class);
        employeeService.login(loginCred.getEmployeeName(), loginCred.getPassword());
        context.json("You are now logged in");
    }


 */
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
