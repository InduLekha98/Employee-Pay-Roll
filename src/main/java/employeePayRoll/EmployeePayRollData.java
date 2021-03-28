package com.employeepayRoll;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeePayRollData {
    public int id;
    public String name,gender,address,phone_no;
    public double salary;
    public LocalDate join_date;

    public EmployeePayRollData(int id, String name, String gender, Double salary, String address, String phone_no, LocalDate date_join) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.address = address;
        this.phone_no = phone_no;
        this.join_date = date_join;
    }

    public EmployeePayRollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    @Override
    public String toString(){
        return "EmployeePayRollData [id:" +id+" name:" +name+ " salary:" + salary+ "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeePayRollData that = (EmployeePayRollData) obj;
        return id == that.id && salary == that.salary && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(join_date, that.join_date);
    }
}

