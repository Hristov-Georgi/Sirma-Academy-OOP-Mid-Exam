package com.sirmaacademy.oopexam.employeemanagementsystem.entity;

import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;

import java.math.BigDecimal;

public class Employee {

    private int id;
    private String name;          // TODO: add full name ??
    private Department department;
    private Role role;
    private BigDecimal salary;

    public Employee(int id, String name, Department department, Role role, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.salary = BigDecimal.valueOf(salary);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Format is compatible with .csv file format.
     * Method is used to properly save the data in .csv file before program finish execution;
     * @return
     */
    @Override
    public String toString() {
        return String.format("id: %d, name: %s, department: %s, role: %s, salary: %s",
                this.id, this.name, this.department.getValue(), this.role.getValue(), this.salary);
    }

}
