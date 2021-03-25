package com.employeepayroll;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayRollFileIOService {
    public static String PAYROLL_FILE_NAME = "C:\\EmployeePayRoll\\src\\main\\resources\\payroll-file.csv";

    public void writeData(List<EmployeePayRollData> employeePayRollDataList){
        StringBuffer empBuffer = new StringBuffer();
        employeePayRollDataList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try{
            Files.write(Paths.get(PAYROLL_FILE_NAME),empBuffer.toString().getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printData(){
        try{
            Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .forEach(System.out::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries(){
        long entries = 0;
        try{
            entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  entries;
    }

    public long countEntries(){
        long entries = 0;
        try{
            entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  entries;
    }


    public List readData() {
        List<String> employeePayRollDataList = new ArrayList<>();
        try{
            Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .map(line -> line.trim())
                    .forEach(line -> employeePayRollDataList.add(line));
        }catch(IOException e){
            e.printStackTrace();
        }
        return employeePayRollDataList;
    }
}