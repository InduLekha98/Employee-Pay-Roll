package employeePayRoll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class EmployeePayRollServiceTest {

    @Test
    public void given3EmployeeWhenWrittenToFile_ShouldReturnEmployeeEntries(){
        EmployeePayRollData[] arrayOfEmployees ={
                new EmployeePayRollData(1,"Bill",1000000.00),
                new EmployeePayRollData(2,"Mark",2000000.00),
                new EmployeePayRollData(3,"Charlie",3000000.00),
        };
        EmployeePayRollService employeePayRollService;
        employeePayRollService = new EmployeePayRollService(Arrays.asList(arrayOfEmployees));
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
}
