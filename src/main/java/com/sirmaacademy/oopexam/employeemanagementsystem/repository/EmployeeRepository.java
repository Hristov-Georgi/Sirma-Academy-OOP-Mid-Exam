package com.sirmaacademy.oopexam.employeemanagementsystem.repository;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * This class handles operations with file - load data from file and
 * at the end of program - save modified data to file.
 */
public class EmployeeRepository {

    /**
     * Create single class instance using Singleton pattern.
     */
    private static EmployeeRepository objectInstance;

    /**
     * Path to the csv file where data is saved and loaded.
     */
    private static final String EMPLOYEES_CSV_FILE = "src/main/resources/employeerepository/employees_data.csv";

    private static final String BROKEN_EMPLOYEE_DATA_FILE = "src/main/resources/employeerepository/broken_employee_data.csv";

    /**
     * Store employees while program is running.
     */
    private List<Employee> employeeList = new ArrayList<>();

    private EmployeeRepository() {
        clearBrokenEmployeeDataContent();
        this.employeeList = loadEmployees();
    }

    /**
     * Return single instance for current class.
     */
    public static EmployeeRepository getInstance(){

        if (objectInstance == null) {
            objectInstance = new EmployeeRepository();
        }
        return objectInstance;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * Add new employee to storage List while program running.
     */
    public void add(Employee employee) {
        this.employeeList.add(employee);
    }

    /**
     * Return List of employees which first name is equal to searched(required) name.
     */
    public List<Employee> findAllByFirstName(String firstName) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getFirstName().equalsIgnoreCase(firstName)){
                employees.add(e);
            }

        }
        return employees;
    }

    /**
     * Return List of employees which last name is equal to searched(required) name.
     */
    public List<Employee> findAllByLastName(String lastName) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getLastName().equalsIgnoreCase(lastName)){
                employees.add(e);
            }

        }
        return employees;
    }

    /**
     * Return List of employees which first and last names are equal to searched(required) names.
     */
    public List<Employee> findByFirstAndLastNames(String firstName, String lastName) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName)){
                employees.add(e);
            }

        }
        return employees;
    }

    /**
     * Return list of Employee if it's id matches to searched(required) id.
     * Throws NoSuchElementException if id not found.
     */
    public List<Employee> findById(int id) {
        List<Employee> employeesById = new ArrayList<>();

        if (id < 1) {
            throw new NoSuchElementException("Id must be >= 1");
        }

        for (Employee e : this.employeeList) {

            if (e.getId() == id){
                employeesById.add(e);
            }

        }

        if (employeesById.isEmpty()) {
            throw new NoSuchElementException("Employee with id: " + id + " not found.");
        } else {
            return employeesById;
        }

    }

    /**
     * Return List of all employees which department matches to searched(required) department.
     * If no employees in the department empty List is returned.
     */
    public List<Employee> findAllByDepartment(Department department) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getDepartment().equals(department)){
                employees.add(e);
            }

        }
        return employees;
    }

    /**
     * Return List of all Active employees currently working for the company.
     * If no Active employees empty List is returned.
     */
    public List<Employee> getAllActive() {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getStatus().equals(Status.ACTIVE)){
                employees.add(e);
            }

        }
        return employees;
    }

    /**
     * Save all employees data in csv file upon exit the program.
     */
    public void persistToFile() {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(EMPLOYEES_CSV_FILE))) {

            for (Employee e : this.employeeList) {
                bufferedWriter.write(e.persistToFilePattern());
                bufferedWriter.newLine();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Save employee which details were modified.
     */
    public void modifyDetails(int id, Employee employee) {
        this.employeeList.remove(id - 1);
        this.employeeList.add(id - 1, employee);
    }

    /**
     * When program start clear "broken_employee_data.csv" file to ensure no old fixed records present.
     */
    private void clearBrokenEmployeeDataContent() {
        try (PrintWriter printWriter = new PrintWriter(BROKEN_EMPLOYEE_DATA_FILE)) {

        } catch (FileNotFoundException ex) {
            System.out.println("File missing");
        }
    }

    /**
     * Load all valid stored employees from csv file when program start.
     * If data is not valid, it is stored in "broken_employee_data.csv file".
     */
    private List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEES_CSV_FILE));
            BufferedWriter writer = new BufferedWriter(new FileWriter(BROKEN_EMPLOYEE_DATA_FILE, true))) {

            String input = bufferedReader.readLine();

            while (input != null) {
                String[] values = input.split(", ");

                try {
                    int id = Integer.parseInt(values[0]);
                    String firstName = values[1];
                    String lastName = values[2];
                    Department department = Department.valueOf(values[3]);
                    Role role = Role.valueOf(values[4]);
                    BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(values[5]));
                    Status status = Status.valueOf(values[6]);

                    Employee employee = new Employee(id, firstName, lastName, department, role, salary, status);
                    employees.add(employee);

                } catch (Exception ex) {
                    writer.write(input);
                    writer.newLine();
                }
                input = bufferedReader.readLine();
            }

        } catch (FileNotFoundException ex){
            System.out.println("File does not exist.");
        } catch (IOException e) {
            System.out.println("Failed to load employees from csv file.");
        }
        return employees;
    }

}
