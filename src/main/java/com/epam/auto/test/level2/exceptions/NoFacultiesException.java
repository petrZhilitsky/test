package com.epam.auto.test.level2.exceptions;

public class NoFacultiesException extends Exception {
    String university;

    public NoFacultiesException(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return university + " has no faculties\n";
    }
}
