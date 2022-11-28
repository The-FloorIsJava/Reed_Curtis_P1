package com.Revature.ReimbursementCode.Service;

import Model.Ticket;
import com.Revature.ReimbursementCode.DAO.TicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    List<Ticket> ticketList;
    private final TicketDAO ticketDAO;
    private Ticket sessionTicket = null;

    public TicketService(TicketDAO ticketDAO) {this.ticketDAO = ticketDAO;}

    public Ticket addTicket(Ticket ticket) {return ticketDAO.create(ticket);}


    public Ticket getTicketFromName(String employeeName) {
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket t = ticketList.get(i);
            if (t.getEmployeeName().equals(employeeName)) {return ticketList.get(i);}
        }
        return null;
    }

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


    public List<Ticket> getAllTickets() {return ticketDAO.findAll();}


    public List<Ticket> approveTicket(int id) {
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket t = ticketList.get(i);
            if (t.getId() == id) {t.setStatus("Approved");
            }
        } return null;
    }


    public List<Ticket> denyTicket(int id) {
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket t = ticketList.get(i);
            if (t.getId() == id) {t.setStatus("Denied");
            }
        }
        return null;
    }
}
