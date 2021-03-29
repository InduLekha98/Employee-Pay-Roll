package com.employeepayRoll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {
    public static Scanner sc = new Scanner(System.in);
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO;
    }

    private List<com.employeepayRoll.EmployeePayRollDataBase> employeePayRollDataList;
    private com.employeepayRoll.EmployeePayRollDataBase employeePayRollDataBase;
    public List<com.employeepayRoll.EmployeePayRollDataBase> employeeList;
    public EmployeePayRollService() {}

    public EmployeePayRollService(List<com.employeepayRoll.EmployeePayRollDataBase> employeeList) {
        this.employeeList = employeeList;
    }


    public long readData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO)) {
            System.out.println("Enter Employee ID: ");
            int id = sc.nextInt();
            System.out.println("Enter Employee Name: ");
            String name = sc.next();
            System.out.println("Enter Employee Salary: ");
            double salary = sc.nextDouble();
            employeeList.add(new com.employeepayRoll.EmployeePayRollDataBase(id, name, salary));
            long result = employeeList.size();
            return result;
        }else if (ioService.equals(IOService.FILE_IO)){
            new EmployeePayRollFileIOService().readData();
            return employeeList.size();
        }else
            return 0;
    }


    public void writeData(IOService ioService) {
        if(ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nOUTPUT:\n" + employeeList);
        else if(ioService.equals(IOService.FILE_IO))
            new EmployeePayRollFileIOService().writeData(employeeList);
    }


    public void printData(IOService ioService){
        if(ioService.equals(IOService.FILE_IO))
            new EmployeePayRollFileIOService().printData();
    }

    public long countEntries(IOService ioService){
        if(ioService.equals(IOService.FILE_IO))
            new EmployeePayRollFileIOService().countEntries();
        return  0;
    }

    public List<com.employeepayRoll.EmployeePayRollDataBase> readEmployeeData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            this.employeeList = new com.employeepayRoll.EmployeePayRollDataBase().readData();
        return this.employeeList;
    }

    public static void main(String[] args) {
        ArrayList<com.employeepayRoll.EmployeePayRollDataBase> employeeList = new ArrayList<>();
        EmployeePayRollService empService = new EmployeePayRollService(employeeList);
        Scanner sc = new Scanner(System.in);
        empService.writeData(IOService.FILE_IO);
        empService.readData(IOService.FILE_IO);
    }
}