package com.employeepayRoll;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollDataBase {
    public EmployeePayRollDataBaseB(){}
    public List<EmployeePayRollData> readData() {
        String sql = "select * from employee_payroll;";
        List<EmployeePayRollData> employeePayrollDataList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                double salary = resultSet.getDouble("salary");
                String phone_no = resultSet.getString("phone_no");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeePayrollDataList.add(new EmployeePayRollData(id, name,gender,salary,address,phone_no,date));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeePayrollDataList;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/ employee_payroll_db?useSSl=false";
        String userName = "root";
        String password = "mysql@1234";
        Connection connection;
        System.out.println("Connecting To DB: " + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL,userName,password);
        System.out.println("Connection is successful..! " + connection);
        return connection;
    }

}