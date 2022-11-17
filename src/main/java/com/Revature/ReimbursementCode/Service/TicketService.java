package com.Revature.ReimbursementCode.Service;

import Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    List<Ticket> ticketList;


    public TicketService(){ticketList = new ArrayList<>();}

    public void addTicket(String employeeName,String type ,double amount,String status){
        Ticket newTicket = new Ticket(employeeName,type,amount,status);
        ticketList.add(newTicket);

    }
}
