package com.sirmaacademy.oopexam.employeemanagementsystem.service;

import com.sirmaacademy.oopexam.employeemanagementsystem.entity.Employee;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;
import com.sirmaacademy.oopexam.employeemanagementsystem.service.employeeserviceimpl.EmployeeServiceLogic;

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
        System.out.println("Type one of the following Integers to execute described command:");
        System.out.println("1 - Add new employee");
        System.out.println("2 - Edit employee");
        System.out.println("3 - Fire employee");
        System.out.println("4 - Search department");
        System.out.println("5 - Search ID");
        System.out.println("6 - Search by first name");
        System.out.println("7 - Search by last name");
        System.out.println("8 - Search by first and last names");
        System.out.println("9 - Display all active employees (non discharged)");
        System.out.println("10 - Exit from menu and close program");
        System.out.println();
    }

    public static void readCommands() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();

            while (input != null) {

                switch (Integer.parseInt(input)) {
                    case 1:
                        System.out.println("Enter employee first name:");
                        String firstName = reader.readLine();
                        System.out.println("Enter employee last name:");
                        String lastName = reader.readLine();
                        System.out.println("Enter department name:");
                        String department = reader.readLine();
                        System.out.println("Enter employee role:");
                        String role = reader.readLine();
                        System.out.println("Enter salary:");
                        double salary = Double.parseDouble(reader.readLine());

                        Employee employee = new Employee(firstName, lastName,
                                Department.valueOf(department.toUpperCase()),
                                Role.valueOf(role.toUpperCase()), salary);

                        EMPLOYEE_SERVICE.add(employee);
//TODO: enums validation and explanation how to be entered. May print list of enums ?
                       //TODO: set values in methods and verify data. That will save last entered data
                        break;
                    case 2:
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

                input = reader.readLine();
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
