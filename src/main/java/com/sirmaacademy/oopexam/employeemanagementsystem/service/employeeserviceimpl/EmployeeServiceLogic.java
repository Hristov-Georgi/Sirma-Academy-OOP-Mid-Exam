package com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;

import java.util.List;

public class EmployeeServiceLogic implements EmployeeService {


    @Override
    public List<Employee> findAllByName(String name) {
        return List.of();
    }

    @Override
    public Employee findById(long id) {
        return null;
    }

    @Override
    public List<Employee> findAllByDepartment(String department) {
        return List.of();
    }
}
