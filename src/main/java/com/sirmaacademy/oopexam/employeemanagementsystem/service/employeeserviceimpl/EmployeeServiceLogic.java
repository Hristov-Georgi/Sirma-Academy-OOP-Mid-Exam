package com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;
import com.sirmaacademy.oopexam.employeemanagementsystem.repository.EmployeeRepository;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeServiceLogic implements EmployeeService {
    private static EmployeeServiceLogic objectInstance;
    /**
     * First id value when first employee is instantiated.
     */
    private static final int INITIAL_ID_VALUE = 1;

    private final EmployeeRepository employeeRepository;

    private EmployeeServiceLogic() {
        this.employeeRepository = EmployeeRepository.getInstance();
    }

    /**
     * Return single class instance using Singleton pattern.
     */
    public static EmployeeServiceLogic getInstance() {

        if (objectInstance == null) {
            objectInstance = new EmployeeServiceLogic();
        }
        return objectInstance;
    }

    /**
     * Return List of employees which first name is equal to searched(required) name.
     */
    @Override
    public List<Employee> findAllByFirstName(String firstName) {
        return this.employeeRepository.findAllByFirstName(firstName);
    }

    /**
     * Return List of employees which last name is equal to searched(required) name.
     */
    @Override
    public List<Employee> findAllByLastName(String lastName) {
        return this.employeeRepository.findAllByLastName(lastName);
    }

    /**
     * Return List of employees which first and last names are equal to searched(required) names.
     */
    @Override
    public List<Employee> findByFirstAndLastNames(String firstName, String lastName) {
        return this.employeeRepository.findByFirstAndLastNames(firstName, lastName);
    }

    /**
     * Return employees if id matches to searched(required) id.
     * Throws NoSuchElementException if id not found.
     */
    @Override
    public List<Employee> findById(int id) {
        return this.employeeRepository.findById(id);
    }

    /**
     * Return List of all employees which department matches to searched(required) department.
     * If no employees in the department empty List is returned.
     */
    @Override
    public List<Employee> findAllByDepartment(Department department) {
        return this.employeeRepository.findAllByDepartment(department);
    }

    /**
     * Return List of all Active employees currently working for the company.
     * If no Active employees empty List is returned.
     */
    @Override
    public List<Employee> getAllActive() {
        return this.employeeRepository.getAllActive();
    }

    /**
     * Add new employee to storage List while program running.
     * Set unique id to every employee.
     */
    @Override
    public void add(String firstName, String lastName, Department department, Role role, double salary) {
        int id = ensureIdUniqueness();
        Employee employee = new Employee(id, firstName, lastName, department, role, salary);
        this.employeeRepository.add(employee);
    }

    /**
     * Modify employee details - first and last names, department, role, salary, status.
     * Throw NoSuchElementException if employee id not found.
     */
    @Override
    public void edit(int id, String firstName, String lastName, Department department, Role role, double salary) {

            List<Employee> employeeList = this.employeeRepository.findById(id);

            if (employeeList.size() > 1) {
                throw new IllegalArgumentException("There are more than one employee with id: " + id);
            } else if (employeeList.isEmpty()) {
                throw new NullPointerException();
            }

            Employee employee = employeeList.getFirst();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setDepartment(department);
            employee.setRole(role);
            employee.setSalary(salary);
            this.employeeRepository.modifyDetails(id, employee);

    }

    /**
     * Change employee status to FIRED when employee no longer work for the company.
     * @throws NoSuchElementException if employee id not found.
     */
    @Override
    public void fire(int id) {

        List<Employee> employeeList = this.employeeRepository.findById(id);

        if (employeeList.size() > 1) {
            throw new IllegalArgumentException("There are more than one employee with id: " + id);
        } else if (employeeList.isEmpty()) {
            throw new NullPointerException("Employee with id: " + id + " not found.");
        }

            Employee employee = employeeList.getFirst();
            employee.setStatus(Status.FIRED);
            this.employeeRepository.modifyDetails(id, employee);
    }

    /**
     * Return List of strings with incorrect employees data read from csv data file.
     */
    @Override
    public List<String> getBrokenEmployeeData() {
        return this.employeeRepository.getBrokenEmployeeData();
    }

    /**
     * Save all employees data in csv file upon exit the program.
     */
    @Override
    public void saveAll() {
        this.employeeRepository.persistToFile();
    }

    /**
     * Return next unique id in series.
     */
    private int ensureIdUniqueness() {
        List<Employee> employeeList = this.employeeRepository.getEmployeeList();
        employeeList.sort(Comparator.comparing(Employee::getId));

        if (employeeList.isEmpty()) {
            return INITIAL_ID_VALUE;
        }
        return employeeList.getLast().getId() + 1;
    }

}
