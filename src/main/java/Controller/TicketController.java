package Controller;

import Model.Ticket;
import com.Revature.ReimbursementCode.DAO.TicketDAO;
import com.Revature.ReimbursementCode.Service.TicketService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public class TicketController{
    TicketService ticketService;
    Javalin app;
    
    public TicketController(){
        ticketService = new TicketService(new TicketDAO());
    }
    
    public void ticketEndpoint(Javalin app){
        app.post("ticket",this::postTicketHandler);
        app.get("ticket",this::getAllTicketsHandler);
        app.get("ticket/{employeeName}",this::getSpecifiedTicketHandler);
        /*
        app.post("ticket/{status}",this::updateTicketStatus);

         */
    }

    private void getSpecifiedTicketHandler(Context context) {
        String employeeName = context.pathParam("employeeName");
        Ticket ticket = ticketService.getTicketFromName(employeeName);
        context.json(ticket);
    }

    private void postTicketHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(context.body(),Ticket.class);
        ticketService.addTicket(ticket);
        context.json(ticket);
    }

    private void getAllTicketsHandler(Context context) {
            List<Ticket> allTickets = ticketService.getAllTickets();
            context.json(allTickets);
    }
   /*
    private void updateTicketStatus(Context context) {
        String status = context.pathParam("status");
        Ticket ticket = ticketService.getTicketFromId();

    }

    */
}
