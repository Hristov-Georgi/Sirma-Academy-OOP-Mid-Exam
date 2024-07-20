package com.sirmaacademy.oopexam.employeemanagementsystem.repository;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    //TODO: fix file path
    private static final String ACTIVE_EMPLOYEES_JSON_FILE = "src/main/resources/employeerepository/activeemployees.txt";
    private static final String FIRED_EMPLOYEES_JSON_FILE = "src/main/resources/employeerepository/firedemployees.txt";

    private static final EmployeeRepository OBJECT_INSTANCE = new EmployeeRepository();

    private List<Employee> activeEmployees;
    private List<Employee> firedEmployees;

    private EmployeeRepository(){
        this.activeEmployees = loadActiveEmployees();
        this.firedEmployees = loadFiredEmployees();
    }

    public EmployeeRepository getInstance() {
        return OBJECT_INSTANCE;
    }

    public List<Employee> getActiveEmployees() {
        return activeEmployees;
    }

    public void setActiveEmployees(List<Employee> activeEmployees) {
        this.activeEmployees = activeEmployees;
    }

    public List<Employee> getFiredEmployees() {
        return firedEmployees;
    }

    public void setFiredEmployees(List<Employee> firedEmployees) {
        this.firedEmployees = firedEmployees;
    }

    public List<Employee> readAll() {

        return null;
    }

    private List<Employee> loadFiredEmployees() {
        List<Employee> firedEmployees = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FIRED_EMPLOYEES_JSON_FILE))) {

            //TODO: read from json format
            //TODO: validate data

        } catch (IOException ex) {
            //TODO: handle exception
        }

        return firedEmployees;

    }

    private List<Employee> loadActiveEmployees() {
        List<Employee> activeEmployees = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(ACTIVE_EMPLOYEES_JSON_FILE))) {

            //TODO: read from json format
            //TODO: validate data


        } catch (IOException ex) {
            //TODO: handle exception
        }

        return activeEmployees;

    }

}
