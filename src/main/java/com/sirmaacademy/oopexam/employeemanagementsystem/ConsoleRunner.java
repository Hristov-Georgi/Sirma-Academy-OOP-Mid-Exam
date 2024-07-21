package com.sirmaacademy.oopexam.employeemanagementsystem;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl.EmployeeServiceLogic;
import com.sirmaacademy.oopexam.employeemanagementsystem.validation.ValidateInputData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ConsoleRunner {

    private static EmployeeService EMPLOYEE_SERVICE = EmployeeServiceLogic.getInstance();

    public static void welcomeMessage() {
        System.out.println("Welcome to Employee Management System");
        System.out.println();
        System.out.println();
    }

    public static void printMenu() {
        System.out.println("Employee Management Menu");
        System.out.println();
        System.out.println("Type one of the following Integers to execute desired command:");
        System.out.println("1 - Add new employee");
        System.out.println("2 - Edit employee");
        System.out.println("3 - Fire employee");
        System.out.println("4 - Search by department");
        System.out.println("5 - Search by ID");
        System.out.println("6 - Search by first name");
        System.out.println("7 - Search by last name");
        System.out.println("8 - Search by first and last names");
        System.out.println("9 - Display all active employees (not fired (discharged))");
        System.out.println("10 - Exit and save all data from Employee Management System");
        System.out.println();
    }

    public static void readCommands() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            boolean readData = true;

            while (readData) {

                try {
                    int n = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid command. Choose command from the menu:");
                    readCommands();
                }

                switch (Integer.parseInt(input)) {
                    case 1:
                        System.out.println("Enter employee first name:");
                        String firstName = ValidateInputData.validateName(reader);
                        System.out.println("Enter employee last name:");
                        String lastName = ValidateInputData.validateName(reader);
                        Department department = ValidateInputData.validateDepartment(reader);
                        Role role = ValidateInputData.validateRole(reader);
                        double salary = ValidateInputData.validateSalary(reader);

                        EMPLOYEE_SERVICE.add(firstName, lastName, department, role, salary);
                        //TODO: return boolean and print if the command is successful ??

//TODO: enums validation and explanation how to be entered. May print list of enums ?
                        break;

                    case 2:
                        printEnterId();
                        String inputId = reader.readLine();

                        try {
                            int id = Integer.parseInt(inputId);
                            System.out.println("Enter employee first name:");
                            String editFirstName = ValidateInputData.validateName(reader);
                            System.out.println("Enter employee last name:");
                            String editLastName = ValidateInputData.validateName(reader);
                            Department editDepartment = ValidateInputData.validateDepartment(reader);
                            Role editRole = ValidateInputData.validateRole(reader);
                            double editSalary = ValidateInputData.validateSalary(reader);

                            EMPLOYEE_SERVICE.edit(id, editFirstName, editLastName, editDepartment, editRole, editSalary);
                        } catch (NullPointerException ex) {
                            System.out.println("Invalid id number: " + inputId + ". Enter valid integer.");
                        }
                        break;

                    case 3:
                        printEnterId();
                        String employeeId = reader.readLine();

                        try {
                            int id = Integer.parseInt(employeeId);
                            EMPLOYEE_SERVICE.fire(id);
                        } catch (NumberFormatException ex) {
                            System.out.println("Enter valid integer.");
                        } catch (NullPointerException ex) {
                            System.out.println("Invalid id number: " + employeeId);
                        }
                        break;

                    case 4:
                        System.out.println("Enter Department name:");
                        Department dep = ValidateInputData.validateDepartment(reader);
                        EMPLOYEE_SERVICE.findAllByDepartment(dep);
                        break;

                    case 5:
                        printEnterId();
                        String id = reader.readLine();

                        try {
                            int idNum = Integer.parseInt(id);
                            Employee employeeById = EMPLOYEE_SERVICE.findById(idNum);
                            System.out.println(employeeById);
                        } catch (NumberFormatException ex) {
                            System.out.println("Enter valid integer.");
                        } catch (NoSuchElementException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Enter employee first name:");
                        String searchedFirstName = ValidateInputData.validateName(reader);
                        List<Employee> employeeByFirstName = EMPLOYEE_SERVICE
                                .findAllByFirstName(searchedFirstName);

                        if (!employeeByFirstName.isEmpty()) {
                            printListData(employeeByFirstName);
                        } else {
                            System.out.println("Company does not have employees with first name " + searchedFirstName);
                        }

                        break;

                    case 7:
                        System.out.println("Enter employee last name:");
                        String searchedLastName = ValidateInputData.validateName(reader);
                        List<Employee> employeeByLastName = EMPLOYEE_SERVICE.findAllByLastName(searchedLastName);

                        if (!employeeByLastName.isEmpty()) {
                            printListData(employeeByLastName);
                        } else {
                            System.out.println("Company does not have employees with last name " + searchedLastName);
                        }
                        break;

                    case 8:
                        System.out.println("Enter employee first and last name, separated by empty space. (Example: Ivan Ivanov):");
                        String names = reader.readLine();
                        String fName = names.split("\\s+")[0];
                        String lName = names.split("\\s+")[1];

                        try {
                            ValidateInputData.validateNames(fName);
                            ValidateInputData.validateNames(lName);
                            List<Employee> employeesByFAndLNames = EMPLOYEE_SERVICE
                                    .findByFirstAndLastNames(fName, lName);

                            if (employeesByFAndLNames.isEmpty()) {
                                printListData(employeesByFAndLNames);
                            } else {
                                System.out.printf("Employees with first name %s and last name %s were not found.%n",
                                        fName, lName);
                            }

                        } catch (IllegalArgumentException | InvalidObjectException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 9:
                        List<Employee> activeEmployees = EMPLOYEE_SERVICE.getAllActive();
                        printListData(activeEmployees);
                        break;

                    case 10:
                        EMPLOYEE_SERVICE.saveAll();
                        readData = false;
                        break;

                    default:
                        return;
                }
                System.out.println("Enter new command:");
                input = reader.readLine();

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void printEnterId() {
        System.out.println("Enter employee id:");
    }

    private static <T> void printListData(List<T> listData) {
        for (T e : listData) {
            System.out.println(e.toString());
        }

    }

}
