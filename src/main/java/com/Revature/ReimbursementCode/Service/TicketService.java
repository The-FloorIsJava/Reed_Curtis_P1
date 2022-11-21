package com.Revature.ReimbursementCode.Service;

import Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    List<Ticket> ticketList;


    public TicketService(){ticketList = new ArrayList<>();}

    public void addTicket(String employeeName,String type,double amount,String status, int id){
        Ticket newTicket = new Ticket(employeeName,type,amount,status,id);
        ticketList.add(newTicket);
    }

    public void addTicket(Ticket ticket){ticketList.add(ticket);}
    public Ticket getTicket(String employeeName){
       for(int i =0;i<ticketList.size();i++){
           Ticket t = ticketList.get(i);
           if(t.getEmployeeName().equals(employeeName)){return ticketList.get(i);}
       } return null;
    }
    public void removeTicket(int id){
        for(int i=0;i<ticketList.size();i++){
            Ticket t = ticketList.get(i);
            if(t.getId() == id){ ticketList.remove(i);}
        }
    }

    public List<Ticket> getAllTickets(){return ticketList;}
}
