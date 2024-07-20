package com.sirmaacademy.oopexam.employeemanagementsystem.service;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllByFirstName(String firstName);
    List<Employee> findAllByLastName(String lastName);
    List<Employee> findByFullName(String firstName, String lastName);
    Employee findById(int id);
    List<Employee> findAllByDepartment(String department);
    List<Employee> getAllActive();
    void add(Employee employee);
    void edit(); // TODO: Modify existing employee details - which details.
    void fire();
    void saveAllActive();
    void saveAllFired();

}
