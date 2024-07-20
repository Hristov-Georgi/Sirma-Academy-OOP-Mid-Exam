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
    public List<Employee> findAllByFirstName(String firstName) {
        return this.employeeRepository.findAllByFirstName(firstName);
    }

    @Override
    public List<Employee> findAllByLastName(String lastName) {
        return this.employeeRepository.findAllByLastName(lastName);
    }

    @Override
    public List<Employee> findByFullName(String firstName, String lastName) {
        return this.employeeRepository.findByFullName(firstName, lastName);
    }

    @Override
    public Employee findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAllByDepartment(String department) {
        return this.employeeRepository.findAllByDepartment(department);
    }

    @Override
    public List<Employee> getAllActive() {
        return this.employeeRepository.getAllActive();
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
