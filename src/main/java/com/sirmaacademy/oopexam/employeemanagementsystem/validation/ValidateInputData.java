package com.sirmaacademy.oopexam.employeemanagementsystem.validation;

import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;

import javax.swing.text.ChangedCharSetException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InvalidObjectException;

public abstract class ValidateInputData {
    private static final double MINIMAL_SALARY = 933;

    public static double validateSalary(BufferedReader reader) throws IOException {
        System.out.println("Enter salary:");
        String input = reader.readLine();

        try {
            double salary = Double.parseDouble(input);

            if (salary < MINIMAL_SALARY) {
                System.out.println("Salary should not be less than " + MINIMAL_SALARY);
                validateSalary(reader);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid salary format. Enter valid number");
            validateSalary(reader);
        }
        return Double.parseDouble(input);
        //TODO: should return string ??
    }

    public static Role validateRole(BufferedReader reader) throws IOException {
        System.out.println("Enter employee role:");
        String input = reader.readLine();

        Role[] values = Role.values();

        for (Role r : values) {

            if (r.getValue().equalsIgnoreCase(input)) {
                return Role.valueOf(String.valueOf(r));
            }

        }
        System.out.println("Invalid Role.");
        return validateRole(reader);
        //TODO: return enum
    }

    public static Department validateDepartment(BufferedReader reader) throws IOException {
        System.out.println("Enter department name:");
        String input = reader.readLine();

        Department[] values = Department.values();

        for (Department v : values) {

            if (v.getValue().equalsIgnoreCase(input)) {
                return Department.valueOf(String.valueOf(v));
            }

        }
        System.out.println("Invalid Department.");
        return validateDepartment(reader);
        //TODO: return enum
    }

    public static String validateName(BufferedReader reader) throws IOException {
        String name = reader.readLine();

        if (name.isBlank() || name.length() < 2) {
            System.out.println("Name should be 2 or more symbols long. Enter valid name:");
            validateName(reader);
        }

        for (char s : name.toCharArray()) {

            if (s < 65 || 90 < s && s < 97 || 122 < s) {
                System.out.println("Name should contain only letters. Enter valid name:");
                validateName(reader);
            }

        }
        return name.trim();
    }

    public static boolean validateNames(String name) throws IOException {

        if (name.isBlank() || name.length() < 2) {
            throw new InvalidObjectException("Name should be 2 or more symbols long. Enter valid name:");
        }

        for (char s : name.toCharArray()) {

            if (s < 65 || 90 < s && s < 97 || 122 < s) {
                throw new IllegalArgumentException("Name should contain only letters. Enter valid name:");
            }

        }
        return true;
    }

}
