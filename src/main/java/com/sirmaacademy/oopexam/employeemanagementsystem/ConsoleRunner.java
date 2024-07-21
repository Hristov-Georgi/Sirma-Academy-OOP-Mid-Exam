package com.sirmaacademy.oopexam.employeemanagementsystem;

import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.EmployeeService;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl.EmployeeServiceLogic;
import com.sirmaacademy.oopexam.employeemanagementsystem.validation.ValidateInputData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        System.out.println("4 - Search department");
        System.out.println("5 - Search ID");
        System.out.println("6 - Search by first name");
        System.out.println("7 - Search by last name");
        System.out.println("8 - Search by first and last names");
        System.out.println("9 - Display all active employees (non discharged)");
        System.out.println("10 - Save all manipulated data and exit from Employee Management System");
        System.out.println();
    }

    public static void readCommands() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();

            while (input != null) {

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
                        System.out.println("Enter employee id:");
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
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
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

}
