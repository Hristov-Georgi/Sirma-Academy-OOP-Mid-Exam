package com.sirmaacademy.oopexam.employeemanagementsystem.enums;


public enum Department {

    IT("IT"),
    MARKETING("Marketing"),
    HUMAN_RESOURCES("Human Resources"),
    ACCOUNTING("Accounting"),
    SECURITY("Security");

    private String value;

    private Department(String value) {
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
