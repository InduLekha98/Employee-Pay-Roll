
package com.employeepayRoll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class EmployeePayRollServiceTest {

    @Test
    public void given3EmployeeWhenWrittenToFile_ShouldReturnEmployeeEnteries(){
        EmployeePayRollData[] arrayOfEmps ={
                new EmployeePayRollData(101,"Indu",1100000.00),
                new EmployeePayRollData(202,"Lekha",2000000.00),
                new EmployeePayRollData(303,"Sandhya",3000000.00),
        };
        EmployeePayRollService employeePayRollService;
        employeePayRollService = new EmployeePayRollService(Arrays.asList(arrayOfEmps));
        employeePayRollService.writeData(EmployeePayRollService.IOService.FILE_IO);
        employeePayRollService.printData(EmployeePayRollService.IOService.FILE_IO);
        long entries = employeePayRollService.countEntries(EmployeePayRollService.IOService.FILE_IO);
        Assertions.assertEquals(3,entries);
    }

    @Test
    public void givenFileReadingFromFile_shouldMatchEmployeeCount(){
        EmployeePayRollService employeePayRollService = new EmployeePayRollService();
        long entries = employeePayRollService.readData(EmployeePayRollService.IOService.FILE_IO);
        Assertions.assertEquals(3,entries);
    }

    @Test
    void givenEmployeePayRollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayRollService employeePayrollService = new EmployeePayRollService();
        List<EmployeePayRollData> employeePayrollData = employeePayrollService.readEmployeeData(EmployeePayRollService.IOService.DB_IO);
        Assertions.assertEquals(3, employeePayrollData.size());
    }

    @Test
    void givenNewSalaryForEmployee_whenUpdate_shouldSyncWithDB() {
        EmployeePayRollService employeePayrollService = new EmployeePayRollService();
        List<EmployeePayRollData> employeePayrollData = employeePayrollService.readEmployeeData(EmployeePayRollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalary("TERISA", 30000000.00);
        boolean result = employeePayrollService.checkEmployeePayRollSyncWithDB("TERISA");
        Assertions.assertEquals(true, result);
    }

    @Test
    void givenDateRangeToEmployeePayRollInDB_WhenRetrieved_ShouldMatchFilteredEmployeeCount() {
        EmployeePayRollService employeePayrollService = new EmployeePayRollService();
        String date = "2018-01-01";
        String endDate= "2020-12-22";
        List<EmployeePayRollData> employeePayrollData = employeePayrollService.readFilteredEmpPayRollData(EmployeePayRollService.IOService.DB_IO,date,endDate);
        Assertions.assertEquals(3,employeePayrollData.size());
    }
}