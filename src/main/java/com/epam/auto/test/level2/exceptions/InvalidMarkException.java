package com.epam.auto.test.level2.exceptions;

public class InvalidMarkException extends Exception{
    String student;
    int mark;

    public InvalidMarkException(String student, int mark) {
        this.student = student;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return student + " has the mark out of interval from 0 to 10: " + mark + '\n';
    }
}
