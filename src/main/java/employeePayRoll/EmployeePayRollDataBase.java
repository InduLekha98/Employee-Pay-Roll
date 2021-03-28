package com.employeepayRoll;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollDataBase {
    public EmployeePayRollDataBase(){}
    private PreparedStatement employeePayRollDataStatement;
    private static EmployeePayRollDataBase employeePayRollDataBase;


    public static EmployeePayRollDataBase getInstance(){
        if (employeePayRollDataBase==null)
            employeePayRollDataBase=new EmployeePayRollDataBase();
        return employeePayRollDataBase;
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

    public List<EmployeePayRollDataBase> readData() {
        String sql = "select * from employee_system;";
        List<EmployeePayRollDataBase> employeePayRollDataBaseList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayRollDataBaseList = this.getEmployeePayRollData(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employeePayRollDataBaseList;
    }

    private List<EmployeePayRollDataBase> getEmployeePayRollData(ResultSet resultSet) {
        List<EmployeePayRollDataBase>employeePayRollDataBaseList = new ArrayList<>();
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                double salary = resultSet.getDouble("salary");
                String phone_no = resultSet.getString("phone_no");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeePayRollDataBaseList.add(new EmployeePayRollDataBase(id,name,gender,salary,address,phone_no,date));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayRollDataBaseList;
    }

    public List<EmployeePayRollDataBase> getEmployeePayRollData(String name) {
        List<EmployeePayRollDataBase>employeePayRollDataBaseList = null;
        if (this.employeePayRollDataStatement==null)
            this.preparedStatementForEmployeeData();
        try {
            employeePayRollDataStatement.setString(1,name);
            ResultSet resultSet = employeePayRollDataStatement.executeQuery();
            employeePayRollDataBaseList = this.getEmployeePayRollData(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayRollDataBaseList;
    }

    private void preparedStatementForEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "select * from employee_system where name= ?;";
            employeePayRollDataStatement = connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int updateEmployeeDatBase(String name, double salary) {
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

    public List<EmployeePayRollDataBase> readFilteredData(String date,String endDate) {
        String sql = String.format("select * from employee_system where start between '%s' and '%s';",date,endDate);
        List<EmployeePayRollDataBase> employeePayRollDataBasesList = new ArrayList<>();
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayRollDataBaseList = this.getEmployeePayRollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayRollDataBaseList;
    }
    //To get sum of the salary of given gender.
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
    //Average of salary
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

    public EmployeePayRollDataBase addEmployee(String name, double salary, LocalDate date){
        int id = -1;
        String sql = String.format("insert into employee_system(name,salary,date_join)value('%s','%s','%s','%s')", name,salary,Date.valueOf(date));
        EmployeePayRollDataBase employeePayrollData = null;
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sql,statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next())
                    id = resultSet.getInt(1);
            }
            employeePayRollDataBase = new EmployeePayRollDataBase(id,name,salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayRollDataBase;
    }
}