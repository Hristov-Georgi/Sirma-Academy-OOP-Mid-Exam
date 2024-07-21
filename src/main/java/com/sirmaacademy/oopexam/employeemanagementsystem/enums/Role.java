package com.sirmaacademy.oopexam.employeemanagementsystem.enums;

public enum Role {

    JUNIOR_JAVA_DEVELOPER ("Junior Java Developer"),
    MID_JAVA_DEVELOPER ("Mid Java Developer"),
    SENIOR_JAVA_DEVELOPER ("Senior Java Developer"),
    JUNIOR_FRONT_END_DEVELOPER ("Junior Front-End Developer"),
    FRONT_END_DEVELOPER ("Front-End Developer"),
    MID_FRONT_END_DEVELOPER ("Mid Front-End Developer"),
    SENIOR_FRONT_END_DEVELOPER ("Senior Front-End Developer"),
    FULL_STACK_DEVELOPER ("Full Stack Developer"),
    ACCOUNTANT ("Accountant"),
    SECURITY_ENGINEER ("Security Engineer"),
    MARKETING_MANAGER ("Marketing Manager"),
    RECRUITMENT ("Recruitment"),
    EMPLOYEE_RELATIONS ("Employee Relations");

    private String value;

    private Role (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", this.ordinal() + 1, this.getValue());
    }

}
