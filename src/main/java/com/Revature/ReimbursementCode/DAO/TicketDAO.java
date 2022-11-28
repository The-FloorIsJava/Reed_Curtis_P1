package com.Revature.ReimbursementCode.DAO;

import Model.Ticket;
import com.Revature.ReimbursementCode.UTIL.ConnectionFactory;
import com.Revature.ReimbursementCode.UTIL.Crudable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements Crudable<Ticket> {
    public Ticket create(Ticket newTicket) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "INSERT INTO tickets (employee_name,amount,description) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newTicket.getEmployeeName());
            preparedStatement.setDouble(2, newTicket.getAmount());
            preparedStatement.setString(3, newTicket.getDescription());

            int checkInsert = preparedStatement.executeUpdate();
            if (checkInsert == 0) {
                throw new RuntimeException("Ticket was not added.");
            }

            return newTicket;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(int id) {return false;}

    public boolean update(Ticket updatedTicket){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "UPDATE tickets SET status = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,updatedTicket.getStatus());
            preparedStatement.setInt(2,updatedTicket.getId());
            int check = preparedStatement.executeUpdate();
            if (check == 0){
                return false;
            } else {return true;}
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    public List<Ticket> findByName(String employeeName){
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Ticket> tickets = new ArrayList<>();
            String sql = "SELECT * FROM tickets WHERE employee_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                tickets.add(convertSqlInfoToTicket(resultSet));
            }
            return tickets;
                } catch(SQLException e) {
                    e.printStackTrace();
                    return null;
        }
    }

    public Ticket findById(int id){return null;}

    public List<Ticket> findAll() {
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Ticket> tickets = new ArrayList<>();
            String sql = "SELECT * FROM tickets WHERE status = 'Pending'";
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
        ticket.setEmployeeName(resultSet.getString("employee_name"));
        ticket.setId(resultSet.getInt("id"));
        ticket.setStatus(resultSet.getString("status"));
        ticket.setAmount(resultSet.getDouble("amount"));
        ticket.setDescription(resultSet.getString("description"));
        return ticket;
    }
}
