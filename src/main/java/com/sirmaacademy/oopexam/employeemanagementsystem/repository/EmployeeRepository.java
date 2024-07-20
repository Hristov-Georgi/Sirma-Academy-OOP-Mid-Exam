package com.sirmaacademy.oopexam.employeemanagementsystem.repository;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Status;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EmployeeRepository {

    private static final String EMPLOYEES_CSV_FILE = "src/main/resources/employeerepository/employees_data.csv";

    private static final EmployeeRepository OBJECT_INSTANCE = new EmployeeRepository();

    private List<Employee> employeeList;

    private EmployeeRepository(){
        this.employeeList = loadEmployees();
    }

    public static EmployeeRepository getInstance() {
        return OBJECT_INSTANCE;
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

    public List<Employee> findByFullName(String firstName, String lastName) {
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

    public List<Employee> findAllByDepartment(String department) {
        List<Employee> employees = new ArrayList<>();

        for (Employee e : employeeList) {

            if (e.getDepartment().getValue().equals(department)){
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

    private List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(EMPLOYEES_CSV_FILE))) {

            //TODO: read from json format
            //TODO: validate data


        } catch (IOException ex) {
            //TODO: handle exception
        }
        return employees;

    }

}
