package com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.repository.EmployeeRepository;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;

import java.util.List;

public class EmployeeServiceLogic implements EmployeeService {
    private static final EmployeeServiceLogic OBJECT_INSTANCE = new EmployeeServiceLogic();

    private final EmployeeRepository employeeRepository;

    private EmployeeServiceLogic() {
        this.employeeRepository = EmployeeRepository.getInstance();
    }

    public static EmployeeServiceLogic getInstance() {
        return OBJECT_INSTANCE;
    }

    @Override
    public List<Employee> findAllByName(String name) {

        return this.employeeRepository.findAllByName(name);
    }

    @Override
    public Employee findById(long id) {
        return null;
    }

    @Override
    public List<Employee> findAllByDepartment(String department) {
        return List.of();
    }

    @Override
    public List<Employee> getAllActive() {
        return List.of();
    }

    @Override
    public void add(Employee employee) {

    }

    @Override
    public void edit() {

    }

    @Override
    public void fire() {

    }

    @Override
    public void saveAllActive() {

    }

    @Override
    public void saveAllFired() {

    }
}
