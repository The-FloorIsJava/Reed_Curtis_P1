package com.Revature.ReimbursementCode.DAO;

import Model.Employee;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.Revature.ReimbursementCode.UTIL.ConnectionFactory;
import com.Revature.ReimbursementCode.UTIL.Crudable;


public class EmployeeDAO implements Crudable<Employee> {

    public Employee create(Employee newEmployee) {
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newEmployee.getUserName());
            preparedStatement.setString(2, newEmployee.getUserPassword());

        } catch (SQLException e) {

        }
        return null;
    }
    public boolean delete(int id){return false;}
    public boolean update(Employee updatedEmployee){return false;}
    public Employee findById(int id){return null;}
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

    private Employee convertSqlInfoToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setUserName(resultSet.getString("username"));
        employee.setUserPassword(resultSet.getString("user_password"));
        return employee;
    }
/*
    public Employee loginCheck(String userName, String userPassword){
        try(Connection connection = ConnectionFactory.getConnectionFactory().getConnection()){
            String sql = "select * from employee where username = ? and password = ?";
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

 */

}
