package Controller;

import Model.Ticket;
import com.Revature.ReimbursementCode.DAO.TicketDAO;
import com.Revature.ReimbursementCode.Service.EmployeeService;
import com.Revature.ReimbursementCode.Service.TicketService;
import com.Revature.ReimbursementCode.UTIL.DTO.NotManagerException;
import com.Revature.ReimbursementCode.UTIL.InvalidEmployeeInputException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public class TicketController{
    TicketService ticketService;
    EmployeeService employeeService;

    Javalin app;
    
    public TicketController(EmployeeService employeeService){
        this.employeeService = employeeService;
        ticketService = new TicketService(new TicketDAO());
    }
    
    public void ticketEndpoint(Javalin app){
        app.post("ticket",this::postTicketHandler);
        app.get("ticket",this::getAllTicketsHandler);
        app.get("ticket/{employeeName}",this::getSpecifiedTicketHandler);
        app.post("ticket/{id}",this::postUpdatedTicketHandler);
    }

    private void postUpdatedTicketHandler(Context context) throws JsonProcessingException{
        if(authCheck(context)) return;
        ObjectMapper mapper = new ObjectMapper();
        Ticket updatedTicket = mapper.readValue(context.body(),Ticket.class);
        ticketService.updateThisTicket(updatedTicket);
        context.json(updatedTicket);
    }

    private void getSpecifiedTicketHandler(Context context) {
        String employeeName = context.pathParam("employeeName");
        List<Ticket> tickets = ticketService.getTicketFromName(employeeName);
        context.json(tickets);
    }

    private void postTicketHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(context.body(),Ticket.class);
        ticketService.addTicket(ticket);
        context.json(ticket);
    }

    private void getAllTicketsHandler(Context context) {
        if(employeeService.roleCheck() == false){
            throw new InvalidEmployeeInputException("You do not have permission to do that.");
        }
        List<Ticket> allTickets = ticketService.getAllTickets();
        context.json(allTickets);
    }
    public boolean authCheck(Context context){
        if(!employeeService.roleCheck()) {
            context.json("You do not have permission to do that.");
            context.status(403);
            return true;
        } return false;
    }
}
