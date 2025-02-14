package com.sirmaacademy.oopexam.employeemanagementsystem;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl.EmployeeServiceLogic;
import com.sirmaacademy.oopexam.employeemanagementsystem.validation.ValidateInputData;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handles all input data and print result from selected command.
 */
public abstract class ConsoleRunner {

    private static final EmployeeService EMPLOYEE_SERVICE = EmployeeServiceLogic.getInstance();

    /**
     * Print welcome message when program start.
     */
    public static void welcomeMessage() {
        System.out.println();
        System.out.println("Welcome to Employee Management System");
        System.out.println();
    }

    /**
     * Print program Menu.
     */
    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println();
        System.out.println("1 - Add new employee");
        System.out.println("2 - Edit employee - modify existing employee details");
        System.out.println("3 - Fire employee");
        System.out.println("4 - Search by department");
        System.out.println("5 - Search by ID");
        System.out.println("6 - Search by first name");
        System.out.println("7 - Search by last name");
        System.out.println("8 - Search by first and last names");
        System.out.println("9 - Display all active employees (not fired (discharged))");
        System.out.println("10 - Display all Roles");
        System.out.println("11 - Display all Departments");
        System.out.println("12 - Exit and save all data from Employee Management System");
    }

    /**
     * Print incorrect entered employees data if such exist.
     */
    public static void printIncorrectEmployeeData() {
        List<String> brokenData = EMPLOYEE_SERVICE.getBrokenEmployeeData();

        if (!brokenData.isEmpty()) {
            System.out.println();
            System.out.println("Found incorrect employees data:");
            System.out.println();
            printListData(brokenData);
            System.out.println("Please fix employees data. Check broken_employee_data.csv file for data details.");
            System.out.println("Only correct employees data will be saved upon exit.");
        }

    }

    /**
     * Read commands and calls executable methods.
     */
    public static void readCommands() {


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean readData = true;

            while (readData) {
                System.out.println();
                System.out.println("Enter new integer command from the Menu:");
                String input = reader.readLine();

                /**
                 * Check if the input command is correct.
                 */
                try {
                    int n = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid command \"" + input + "\"");
                    continue;
                }

                switch (Integer.parseInt(input)) {

                    case 1:
                        try {
                            printEnterFirstName();
                            String firstName = reader.readLine();
                            ValidateInputData.validateName(firstName);

                            printEnterLastName();
                            String lastName = reader.readLine();
                            ValidateInputData.validateName(lastName);

                            printEnterDepartment();
                            String inputDepartment = reader.readLine();
                            Department department = ValidateInputData.validateDepartment(inputDepartment);

                            printEnterRole();
                            String inputRole = reader.readLine();
                            Role role = ValidateInputData.validateRole(inputRole);

                            printEnterSalary();
                            String inputSalary = reader.readLine();
                            double salary = ValidateInputData.validateSalary(inputSalary);

                            EMPLOYEE_SERVICE.add(firstName, lastName, department, role, salary);
                        } catch (InvalidObjectException | IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 2:
                        printEnterId();
                        String inputId = reader.readLine();

                        try {
                            int id = Integer.parseInt(inputId);

                            printEnterFirstName();
                            String editFirstName = reader.readLine();
                            ValidateInputData.validateName(editFirstName);

                            printEnterLastName();
                            String editLastName = reader.readLine();
                            ValidateInputData.validateName(editLastName);

                            printEnterDepartment();
                            String inputDepartment = reader.readLine();
                            Department editDepartment = ValidateInputData.validateDepartment(inputDepartment);

                            printEnterRole();
                            String inputRole = reader.readLine();
                            Role role = ValidateInputData.validateRole(inputRole);

                            printEnterSalary();
                            String inputSalary = reader.readLine();
                            double editSalary = ValidateInputData.validateSalary(inputSalary);

                            EMPLOYEE_SERVICE.edit(id, editFirstName, editLastName, editDepartment, role, editSalary);
                        } catch (NullPointerException ex) {
                            System.out.println("Invalid id number: " + inputId + ". Enter valid integer.");
                        } catch (InvalidObjectException | IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
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
                        } catch (NoSuchElementException exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;

                    case 4:
                        printEnterDepartment();
                        String depName = reader.readLine();
                        Department dep = ValidateInputData.validateDepartment(depName);
                        List<Employee> employeesByDepartment = EMPLOYEE_SERVICE.findAllByDepartment(dep);

                        if (!employeesByDepartment.isEmpty()) {
                            printListData(employeesByDepartment);
                        } else {
                            System.out.println("There are no employees working in " + dep.getValue() + " department");
                        }
                        break;

                    case 5:
                        printEnterId();
                        String id = reader.readLine();

                        try {
                            int idNum = Integer.parseInt(id);
                            List<Employee> employeeById = EMPLOYEE_SERVICE.findById(idNum);
                            printListData(employeeById);
                        } catch (NumberFormatException ex) {
                            System.out.println("Enter valid integer.");
                        } catch (NoSuchElementException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    case 6:
                        printEnterFirstName();
                        String searchedFirstName = reader.readLine();
                        ValidateInputData.validateName(searchedFirstName);

                        List<Employee> employeeByFirstName = EMPLOYEE_SERVICE
                                .findAllByFirstName(searchedFirstName);

                        if (!employeeByFirstName.isEmpty()) {
                            printListData(employeeByFirstName);
                        } else {
                            System.out.println("Company does not have employees with first name "
                                    + searchedFirstName);
                        }
                        break;

                    case 7:
                        printEnterLastName();
                        String searchedLastName = reader.readLine();
                        ValidateInputData.validateName(searchedLastName);

                        List<Employee> employeeByLastName = EMPLOYEE_SERVICE.findAllByLastName(searchedLastName);

                        if (!employeeByLastName.isEmpty()) {
                            printListData(employeeByLastName);
                        } else {
                            System.out.println("Company does not have employees with last name "
                                    + searchedLastName);
                        }
                        break;

                    case 8:
                        System.out.println("Enter employee first and last name, separated by empty space. (Example: Ivan Ivanov):");
                        String names = reader.readLine();
                        String fName = names.split("\\s+")[0];
                        String lName = names.split("\\s+")[1];

                        try {
                            ValidateInputData.validateName(fName);
                            ValidateInputData.validateName(lName);

                            List<Employee> employeesByFAndLNames = EMPLOYEE_SERVICE
                                    .findByFirstAndLastNames(fName, lName);

                            if (!employeesByFAndLNames.isEmpty()) {
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
                        Role[] roles = Role.values();
                        System.out.println("Roles:");
                        printEnumsData(roles);
                        break;

                    case 11:
                        Department[] departments = Department.values();
                        System.out.println("Departments:");
                        printEnumsData(departments);
                        break;

                    case 12:
                        EMPLOYEE_SERVICE.saveAll();
                        readData = false;
                        break;

                    default:
                        return;
                }

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void printEnterId() {
        System.out.println("Enter employee id:");
    }

    private static void printEnterFirstName() {
        System.out.println("Enter employee first name:");
    }

    private static void printEnterLastName() {
        System.out.println("Enter employee last name:");
    }

    private static void printEnterDepartment() {
        System.out.println("Enter Department name:");
    }

    private static void printEnterRole() {
        System.out.println("Enter Role name:");
    }

    private static void printEnterSalary() {
        System.out.println("Enter salary:");
    }

    /**
     * Print all values from a given List.
     */
    private static <T> void printListData(List<T> listData) {
        for (T e : listData) {
            System.out.println(e.toString());
        }

    }

    /**
     * Print all values from a given enumeration.
     */
    private static<T> void printEnumsData(T[] enumArr) {

        for (T e : enumArr) {
            System.out.println(e.toString());
        }

    }

}
