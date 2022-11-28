package com.Revature.ReimbursementCode.DAO;

import Model.Employee;
import Model.Ticket;
import com.Revature.ReimbursementCode.UTIL.ConnectionFactory;
import com.Revature.ReimbursementCode.UTIL.Crudable;
import com.Revature.ReimbursementCode.UTIL.InvalidEmployeeInputException;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements Crudable<Ticket> {
    public Ticket create(Ticket newTicket){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "INSERT INTO tickets (employee_name,amount,description) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newTicket.getEmployeeName());
            preparedStatement.setDouble(2,newTicket.getAmount());
            preparedStatement.setString(3,newTicket.getDescription());

           int checkInsert = preparedStatement.executeUpdate();
           if(newTicket.getDescription() == null || newTicket.getAmount() == 0.00){
               throw new RuntimeException("Ticket must have an amount and description.");
           }
           if(checkInsert == 0){
               throw new RuntimeException("Ticket was not added.");
           }

            return newTicket;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(int id) {return false;}

    public boolean update(Ticket updatedTicket){return false;}

    public Ticket findById(int id){return null;}

    public List<Ticket> findAll(){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Ticket> tickets = new ArrayList<>();
            String sql = "SELECT * FROM tickets WHERE status = 'Pending'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.getString("user_role") != "Manager"){
                throw new InvalidEmployeeInputException("You do not have permission to do that.");
            }
            while(resultSet.next()){
                tickets.add(convertSqlInfoToTicket(resultSet));
            }
            return tickets;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private Ticket convertSqlInfoToTicket(ResultSet resultSet) throws SQLException{
        Ticket ticket = new Ticket();
        ticket.setEmployeeName(resultSet.getString("employee_name"));
        ticket.setId(resultSet.getInt("id"));
        ticket.setStatus(resultSet.getString("status"));
        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.setDescription(resultSet.getString("description"));
        return ticket;
    }
}
