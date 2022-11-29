package com.Revature.ReimbursementCode.Service;

import Model.Ticket;
import com.Revature.ReimbursementCode.DAO.TicketDAO;

import java.util.List;

public class TicketService {
    List<Ticket> ticketList;
    private final TicketDAO ticketDAO;
    private Ticket sessionTicket = null;

    public TicketService(TicketDAO ticketDAO) {this.ticketDAO = ticketDAO;}

    public Ticket addTicket(Ticket ticket) {return ticketDAO.create(ticket);}

    public List<Ticket> getTicketFromName(String employeeName) {return ticketDAO.findByName(employeeName);}

    public Ticket getTicketFromId(int id) {
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket t = ticketList.get(i);
            if (t.getId() == id) {return ticketList.get(i);}
        }
        return null;
    }


    public void removeTicket(int id) {
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket t = ticketList.get(i);
            if (t.getId() == id) {
                ticketList.remove(i);
            }
        }
    }


    public List<Ticket> getAllPendingTickets() {return ticketDAO.findAllPending();}
    public List<Ticket> getAllTickets() {return ticketDAO.findAll();}

    public boolean updateThisTicket(Ticket updatedTicket){return ticketDAO.update(updatedTicket);}

}
