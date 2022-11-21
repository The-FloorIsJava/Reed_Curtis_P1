package Controller;

import Model.Ticket;
import com.Revature.ReimbursementCode.Service.TicketService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public class TicketController {
    TicketService ticketService;
    
    public TicketController(){ticketService = new TicketService();}
    
    public void startAPI(){
        Javalin app = Javalin.create().start(8080);
        app.post("ticket",this::postTicketHandler);
        app.get("ticket",this::getAllTicketsHandler);
        app.get("ticket/{employeeName}",this::getSpecifiedTicketHandler);
    }

    private void getSpecifiedTicketHandler(Context context) {
        String employeeName = context.pathParam("employeeName");
        Ticket ticket = ticketService.getTicket(employeeName);
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
}
