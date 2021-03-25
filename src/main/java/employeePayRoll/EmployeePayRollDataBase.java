package employeePayRoll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayRollDataBase {
    private EmployeePayRollDataBase(){}

    /*connecting to the database*/
    private Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll_db?useSSL=false";
        String userName = "root";
        String password = "mysql@1234";
        Connection connection;
        System.out.println("Connecting Database:"+jdbcURL);
        connection = DriverManager.getConnection(jdbcURL,userName,password);
        System.out.println("Connection Successfull!"+connection);
        return connection;
    }
}
