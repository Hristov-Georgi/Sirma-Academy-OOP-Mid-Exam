package com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;
import com.sirmaacademy.oopexam.employeemanagementsystem.repository.EmployeeRepository;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeServiceLogic implements EmployeeService {
    private static final EmployeeServiceLogic OBJECT_INSTANCE = new EmployeeServiceLogic();
    private static final int INITIAL_ID_VALUE = 1;

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
    public List<Employee> findByFirstAndLastNames(String firstName, String lastName) {
        return this.employeeRepository.findByFirstAndLastNames(firstName, lastName);
    }

    @Override
    public Employee findById(int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAllByDepartment(Department department) {
        return this.employeeRepository.findAllByDepartment(department);
    }

    @Override
    public List<Employee> getAllActive() {
        return this.employeeRepository.getAllActive();
    }

    @Override
    public void add(String firstName, String lastName, Department department, Role role, double salary) {
        int id = ensureIdUniqueness();
        Employee employee = new Employee(id, firstName, lastName, department, role, salary);
        this.employeeRepository.add(employee);
    }

    @Override
    public void edit(int id, String firstName, String lastName, Department department, Role role, double salary) {

        try {
            Employee employee = this.employeeRepository.findById(id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDepartment(department);
            employee.setRole(role);
            employee.setSalary(salary);
            this.employeeRepository.modifyDetails(id, employee);
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void fire(int id) throws NoSuchElementException{
            Employee employee = this.employeeRepository.findById(id);
            employee.setStatus(Status.FIRED);
            this.employeeRepository.modifyDetails(id, employee);
    }

    @Override
    public void saveAll() {
        this.employeeRepository.persistToFile();
    }

    private int ensureIdUniqueness() {
        List<Employee> employeeList = this.employeeRepository.getEmployeeList();

        if (employeeList.isEmpty()) {
            return INITIAL_ID_VALUE;
        }
        return employeeList.getLast().getId() + 1;
    }

}
