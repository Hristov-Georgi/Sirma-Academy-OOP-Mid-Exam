package com.sirmaacademy.oopexam.employeemanagementsystem.repository;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeRepository {
    private static final EmployeeRepository OBJECT_INSTANCE = new EmployeeRepository();

    private static final String EMPLOYEES_CSV_FILE = "src/main/resources/employeerepository/employees_data.csv";

    private List<Employee> employeeList = new ArrayList<>();

    private EmployeeRepository(){
        this.employeeList = loadEmployees();
    }

    public static EmployeeRepository getInstance() {
        return OBJECT_INSTANCE;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void add(Employee employee) {
        this.employeeList.add(employee);
    }

    public List<Employee> findAllByFirstName(String firstName) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getFirstName().equals(firstName)){
                employees.add(e);
            }

        }
        return employees;
    }

    public List<Employee> findAllByLastName(String lastName) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getLastName().equals(lastName)){
                employees.add(e);
            }

        }
        return employees;
    }

    public List<Employee> findByFirstAndLastNames(String firstName, String lastName) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getFirstName().equals(firstName) && e.getLastName().equals(lastName)){
                employees.add(e);
            }

        }
        return employees;
    }

    public Employee findById(int id) {
        for (Employee e : employeeList) {

            if (e.getId() == id){
                return e;
            }

        }
        throw new NoSuchElementException("Employee with " + id + " not found.");
    }

    public List<Employee> findAllByDepartment(Department department) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getDepartment().equals(department)){
                employees.add(e);
            }

        }
        return employees;
    }

    public List<Employee> getAllActive() {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getStatus().equals(Status.ACTIVE)){
                employees.add(e);
            }

        }
        return employees;
    }

    public void persistToFile() {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(EMPLOYEES_CSV_FILE))) {
            bufferedWriter.write(this.employeeList.toString());
        } catch (IOException ex) {
            //TODO: is it correct ? or should throw exception ?
            System.out.println(ex.getMessage());
        }

    }

    public void modifyDetails(int id, Employee employee) {
        this.employeeList.remove(id - 1);
        this.employeeList.add(id - 1, employee);
    }

    private List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEES_CSV_FILE))) {
            String input = bufferedReader.readLine();

            while (input != null) {
                String[] values = input.split(", ");

                int id = Integer.parseInt(values[0]);
                String firstName = values[1];
                String lastName = values[2];
                Department department = Department.valueOf(values[3]);
                Role role = Role.valueOf(values[4]);
                BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(values[5]));
                Status status = Status.valueOf(values[6]);

                Employee employee = new Employee(id, firstName, lastName, department, role, salary, status);
                employees.add(employee);

                this.employeeList.add(employee);

                input = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Failed to load employees from csv file.");
        }
        return employees;
    }

}
