package com.Revature.ReimbursementCode.Service;

import Controller.EmployeeController;
import Controller.TicketController;
import com.Revature.ReimbursementCode.DAO.EmployeeDAO;
import io.javalin.Javalin;

public class ReimbursementSystem {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService(new EmployeeDAO());
        Javalin app = Javalin.create().start(8080);

        new EmployeeController(app,employeeService).employeeEndpoint();
        TicketController ticketController = new TicketController(employeeService);

        ticketController.ticketEndpoint(app);

    }
}