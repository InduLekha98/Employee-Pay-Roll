package com.employeepayRoll;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollDataBase {
    public EmployeePayRollDataBase(){}
    private PreparedStatement employeePayRollDataStatement;
    private static EmployeePayRollDBService employeePayRollDBService;


    public static EmployeePayRollDataBaseB getInstance(){
        if (employeePayRollDBService==null)
            employeePayRollDBService=new EmployeePayRollDBService();
        return employeePayRollDBService;
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

    public List<EmployeePayRollData> readData() {
        String sql = "select * from employee_system;";
        List<EmployeePayRollData> employeePayRollDataList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayRollDataList = this.getEmployeePayRollData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeePayRollDataList;
    }

    private List<EmployeePayRollData> getEmployeePayRollData(ResultSet resultSet) {
        List<EmployeePayRollData>employeePayRollDataList = new ArrayList<>();
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                double salary = resultSet.getDouble("salary");
                String phone_no = resultSet.getString("phone_no");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeePayRollDataList.add(new EmployeePayRollData(id,name,gender,salary,address,phone_no,date));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayRollDataList;
    }

    public List<EmployeePayRollData> getEmployeePayRollData(String name) {
        List<EmployeePayRollData>employeePayrollDataList = null;
        if (this.employeePayRollDataStatement==null)
            this.preparedStatementForEmployeeData();
        try {
            employeePayRollDataStatement.setString(1,name);
            ResultSet resultSet = employeePayRollDataStatement.executeQuery();
            employeePayrollDataList = this.getEmployeePayRollData(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollDataList;
    }

    private void preparedStatementForEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "select * from employee_system where name= ?;";
            employeePayRollDataStatement = connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollDataList;
    }

    private void preparedStatementForEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "select * from employee_system where name= ?;";
            employeePayRollDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateEmployeeData(String name, double salary) {
        return this.updateEmployeeDataUsingStatement(name,salary);
    }

    private int updateEmployeeDataUsingStatement(String name, double salary) {
        String sql = String.format("update employee_system set salary = %.2f where name = '%s';",salary,name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<EmployeePayRollData> readFilteredData(String date,String endDate) {
        String sql = String.format("select * from employee_system where start between '%s' and '%s';",date,endDate);
        List<EmployeePayRollData> employeePayRollDataList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayRollDataList = this.getEmployeePayRollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayRollDataList;
    }
    public double sumOfSalary(String gender) {
        String sql = String.format("select sum(salary) from employee_system where gender='%s'", gender);
        double result = 0;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getDouble("sum(salary)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public double avgOfSalary(String gender) {
        String sql = String.format("select avg(salary) as avg_salary from employee_system where gender='%s' group by gender;", gender);
        double result = 0;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getDouble("avg(salary)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int countGender(String gender) {
        String sql = String.format("select count(salary) as count_salary from employee_system where gender='%s' group by gender;\n", gender);
        int result = 0;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getInt("count(gender)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}

}