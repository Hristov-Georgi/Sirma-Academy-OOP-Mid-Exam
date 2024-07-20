package com.sirmaacademy.oopexam.employeemanagementsystem.service;

public abstract class ConsoleInterfaceMenu {

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

}
