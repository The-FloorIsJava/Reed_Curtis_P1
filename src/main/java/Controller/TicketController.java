package Controller;

import com.Revature.ReimbursementCode.Service.EmployeeService;
import io.javalin.Javalin;

public class TicketController {
    EmployeeService employeeService;

    public TicketController(){employeeService = new EmployeeService();}

    public void startAPI(){
        Javalin app = Javalin.create().start(8080);
    }
}
