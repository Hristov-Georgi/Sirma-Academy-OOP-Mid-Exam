package com.sirmaacademy.oopexam.employeemanagementsystem.service;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllByName(String name);
    Employee findById(long id);
    List<Employee> findAllByDepartment(String department);
}
