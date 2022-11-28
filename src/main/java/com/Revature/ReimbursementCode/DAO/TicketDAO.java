package com.Revature.ReimbursementCode.DAO;

import Model.Ticket;
import com.Revature.ReimbursementCode.UTIL.ConnectionFactory;
import com.Revature.ReimbursementCode.UTIL.Crudable;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements Crudable<Ticket> {
    public Ticket create(Ticket newTicket){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,newTicket.getAmount());
            preparedStatement.setString(2,newTicket.getDescription());

           int checkInsert = preparedStatement.executeUpdate();

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
            String sql = "";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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

        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.setDescription(resultSet.getString("description"));
        return ticket;
    }
}
