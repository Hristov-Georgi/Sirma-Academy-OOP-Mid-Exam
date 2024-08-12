package com.sirmaacademy.oopexam.employeemanagementsystem.service;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;

import java.util.List;

public interface EmployeeService {

    /**
     * Return List of all employees which first name match to searched name.
     */
    List<Employee> findAllByFirstName(String firstName);

    /**
     * Return List of all employees which last name match to searched name.
     */
    List<Employee> findAllByLastName(String lastName);

    /**
     * Return List of all employees which first and last names match to searched names.
     */
    List<Employee> findByFirstAndLastNames(String firstName, String lastName);

    /**
     * Return employees which id match to the searched.
     */
    List<Employee> findById(int id);

    /**
     * Return List of all employees in searched department.
     */
    List<Employee> findAllByDepartment(Department department);

    /**
     * Return List of all Active employees currently working in the company.
     */
    List<Employee> getAllActive();

    /**
     * Add new employee in the storage List while program is running.
     */
    void add(String firstName, String lastName, Department department, Role role, double salary);

    /**
     * Modify employee details - first and last names, department, role, salary, status.
     */
    void edit(int id, String firstName, String lastName, Department department, Role role, double salary);

    /**
     * Change employee status to FIRED when employee is fired or left the company.
     */
    void fire(int id);

    /**
     * Save all employees in csv file upon exit the program.
     */
    void saveAll();

}
