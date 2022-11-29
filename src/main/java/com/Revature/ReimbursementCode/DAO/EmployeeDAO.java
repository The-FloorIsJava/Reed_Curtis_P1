package com.Revature.ReimbursementCode.DAO;

import Model.Employee;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.Revature.ReimbursementCode.UTIL.ConnectionFactory;
import com.Revature.ReimbursementCode.UTIL.Crudable;
import com.Revature.ReimbursementCode.UTIL.InvalidEmployeeInputException;

import javax.swing.plaf.nimbus.State;


public class EmployeeDAO implements Crudable<Employee> {

    public Employee create(Employee newEmployee) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "INSERT INTO users (username,user_password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newEmployee.getUserName());
            preparedStatement.setString(2, newEmployee.getUserPassword());

            int checkInsert = preparedStatement.executeUpdate();

            if(checkInsert == 0){
                throw new RuntimeException("Employee was not added.");
            }

            return newEmployee;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }
    public boolean delete(int id){return false;}
    public boolean update(Employee updatedEmployee){return false;}
    public Employee findById(int id){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Employee> employees = new ArrayList<>();
            String sql = "select * from users where user_id=id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                employees.add(convertSqlInfoToEmployee(resultSet));
            }
            return employees.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
    public Employee findByName(String userName){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Employee> employees = new ArrayList<>();
            String sql = "select * from users where username='userName'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                employees.add(convertSqlInfoToEmployee(resultSet));
            }
            return employees.get();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

     */
    public List<Employee> findAll(){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            List<Employee> employees = new ArrayList<>();
            String sql = "select * from users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                employees.add(convertSqlInfoToEmployee(resultSet));
            }
            return employees;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Employee> findAllNames(){
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "SELECT username FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                employees.add(convertSqlInfoToEmployee(resultSet));
            }
            return employees;
        } catch (SQLException e){
            e.printStackTrace();
        } return employees;
    }


    private Employee convertSqlInfoToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setUserId(resultSet.getInt("user_id"));
        employee.setRole(resultSet.getString("user_role"));
        employee.setUserName(resultSet.getString("username"));
        employee.setUserPassword(resultSet.getString("user_password"));
        return employee;
    }

    public Employee loginCheck(String userName, String userPassword){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "select * from users where username = ? and user_password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                throw new InvalidEmployeeInputException("Either Username or Password were incorrect, please check spelling and try again.");
            }return convertSqlInfoToEmployee(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
