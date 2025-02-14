package com.sirmaacademy.oopexam.employeemanagementsystem.entity;

import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;

import java.math.BigDecimal;

/**
 * Used to create employees.
 */
public class Employee {

    private int id;

    /**
     * Decided to separate first and last names for better reading
     * and better data handling.
     */
    private String firstName;
    private String lastName;
    private Department department;
    private Role role;
    private BigDecimal salary;
    private Status status;

    /**
     * Use this constructor only when create new Employee.
     * ID is auto set in the service which creates uniqueness.
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

    public void setSalary(double salary) {
        this.salary = BigDecimal.valueOf(salary);
    }

    /**
     * Format is compatible with .csv file format.
     * Method is used to properly save the data in .csv file before program finish execution;
     */
    public String persistToFilePattern() {
        return String.format("%d, %s, %s, %s, %s, %s, %s",
                this.id, this.firstName, this.lastName, this.department.name(),
                this.role.name(), this.salary, this.status.name());
    }

    /**
     * Method is used to display employee data when required.
     */
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s, %s",
                this.id, this.firstName, this.lastName, this.department.getValue(),
                this.role.getValue(), this.salary, this.status.name());
    }

}
