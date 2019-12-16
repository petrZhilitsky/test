package com.epam.auto.test.level2.exceptions;

public class NoStudentsException extends Exception {
    String group;

    public NoStudentsException(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return group + " group has no students\n";
    }
}
