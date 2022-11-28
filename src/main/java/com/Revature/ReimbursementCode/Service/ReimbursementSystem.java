package com.Revature.ReimbursementCode.Service;

import Controller.EmployeeController;
import Controller.TicketController;
import io.javalin.Javalin;

public class ReimbursementSystem {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(8080);

        new EmployeeController(app).employeeEndpoint();
        TicketController ticketController = new TicketController();

        ticketController.ticketEndpoint(app);

    }
}