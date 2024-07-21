package com.sirmaacademy.oopexam.employeemanagementsystem.service;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllByFirstName(String firstName);
    List<Employee> findAllByLastName(String lastName);
    List<Employee> findByFirstAndLastNames(String firstName, String lastName);
    Employee findById(int id);
    List<Employee> findAllByDepartment(Department department);
    List<Employee> getAllActive();
    void add(String firstName, String lastName, Department department, Role role, double salary);
    void edit(int id, String firstName, String lastName, Department department, Role role, double salary); // TODO: Modify existing employee details - which details.
    void fire(int id);
    void saveAll();

}
