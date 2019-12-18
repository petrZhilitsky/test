package com.epam.auto.test.level2.exceptions;

public class NoGroupsException extends Exception {
    String faculty;

    public NoGroupsException(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return faculty + " faculty has no groups\n";
    }
}
