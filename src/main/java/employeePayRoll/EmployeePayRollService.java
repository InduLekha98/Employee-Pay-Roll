package employeePayRoll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {
    public static Scanner sc = new Scanner(System.in);
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO;
    }

    private List<EmployeePayRollData> employeeList;
    public EmployeePayRollService() {}

    public EmployeePayRollService(List<EmployeePayRollData> employeeList) {
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
            System.out.println("Enter Employee Date: ");
            double Date = sc.nextDouble();
            employeeList.add(new EmployeePayRollData(id, name, salary, Date));
        }else if(ioService.equals(IOService.FILE_IO))
            new EmployeePayRollFileIOService().readDate();
        return 4;
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
        return  4;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayRollData> employeeList = new ArrayList<>();
        EmployeePayRollService empService = new EmployeePayRollService(employeeList);
        Scanner sc = new Scanner(System.in);
        empService.writeData(IOService.FILE_IO);
        empService.readData(IOService.FILE_IO);
    }

}
