package com.sirmaacademy.oopexam.employeemanagementsystem.entity;

import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;

import java.math.BigDecimal;

public class Employee {

    private int id;
    private String firstName;          // TODO: add full name ??
    private String lastName;
    private Department department;
    private Role role;
    private BigDecimal salary;
    private Status status;

    /**
     * Use this constructor only when creating new Employee.
     * ID is auto set which creates uniqueness.
     * Status is auto set to ACTIVE when new employee is added.
     */
    public Employee(int id, String firstName, String lastName, Department department, Role role, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.role = role;
        this.salary = BigDecimal.valueOf(salary);
        this.status = Status.ACTIVE;
    }

    /**
     * Use this constructor when import employees from .csv file with already set values.
     * I am using constructor instead DTO class, because we haven't used mapper (ModelMapper) yet.
     */
    public Employee(int id, String firstName, String lastName, Department department, Role role, BigDecimal salary, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.role = role;
        this.salary = salary;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return String.format("%d, %s, %s, %s, %s, %s, %s",
                this.id, this.firstName, this.lastName, this.department.getValue(),
                this.role.getValue(), this.salary, this.status.name());
    }

}
