package com.epam.auto.test.level2.exceptions;

public class NoSubjectsException extends Exception {
    String student;

    public NoSubjectsException(String student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return student + " has no subjects\n";
    }
}
