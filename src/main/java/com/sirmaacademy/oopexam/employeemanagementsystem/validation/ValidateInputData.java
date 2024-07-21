package com.sirmaacademy.oopexam.employeemanagementsystem.validation;

import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Department;
import com.sirmaacademy.oopexam.employeemanagementsystem.enums.Role;

import java.io.IOException;
import java.io.InvalidObjectException;

public abstract class ValidateInputData {
    private static final double MINIMAL_SALARY = 933;

    //fixed not tested
    public static double validateSalary(String salary) throws NumberFormatException {

        double remuneration = Double.parseDouble(salary);
        if (remuneration < MINIMAL_SALARY) {
            throw new IllegalArgumentException("Salary should not be less than " + MINIMAL_SALARY);
        }
        return remuneration;
    }

    //fixed not checked
    public static Role validateRole(String role) throws IOException {
        Role[] values = Role.values();

        for (Role r : values) {

            if (r.getValue().equalsIgnoreCase(role)) {
                return Role.valueOf(String.valueOf(r));
            }

        }
        throw new IllegalArgumentException("Role \"" + role + "\" does not exist.");
    }

    //fixed not checked
    public static Department validateDepartment(String department) throws IOException {

        Department[] values = Department.values();

        for (Department v : values) {

            if (v.getValue().equalsIgnoreCase(department)) {
                return Department.valueOf(String.valueOf(v));
            }

        }
        throw new IllegalArgumentException("Department \"" + department + "\" does not exist.");
    }

    //fixed not checked
    public static String validateName(String name) throws IOException {

        if (name.isBlank() || name.length() < 2) {
            throw new InvalidObjectException("Name should be 2 or more symbols long. Enter valid name:");
        }

        for (char s : name.toCharArray()) {

            if (s < 65 || 90 < s && s < 97 || 122 < s) {
                throw new IllegalArgumentException("Name should contain only letters. Enter valid name:");
            }

        }
        return name;
    }

}
