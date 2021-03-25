package employeePayRoll;

import java.time.LocalDate;
import java.util.Objects

public class EmployeePayRollData {
    public int id;
    public char name,gender;
    public double salary;
    public LocalDate date;

    public EmployeePayRollData(int id, char name, char gender, Double salary, LocalDate date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.date = date;
    }

    public EmployeePayRollData(int id, char name, double salary, LocalDate date) {
        this.id = id;
        this.name = name;
//        this.gender = gender;
        this.salary = salary;
        this.date = date;
    }

    @Override
    public String toString(){
        return "EmployeePayRollData [id:" +id+" name:" +name+ " salary:" + salary+ " + " date:" + date+" ]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeePayRollData that = (EmployeePayRollData) obj;
        return id == that.id && salary == that.salary && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(date, that.date);
    }
}


