package com.sirmaacademy.oopexam.employeemanagementsystem;

import java.io.Console;

/**
 * Main Employee Management Application.
 */
public class EmployeeManagementApplication {
    public static void main(String[] args) {

        ConsoleRunner.welcomeMessage();
        ConsoleRunner.printMenu();
        ConsoleRunner.printIncorrectEmployeeData();
        ConsoleRunner.readCommands();
    }

}
