package com.epam.auto.test.level2.classes.univercities;

import java.util.Map;

import com.epam.auto.test.level2.enums.Subject;
import com.epam.auto.test.level2.exceptions.NoSubjectsException;
import com.epam.auto.test.level2.exceptions.InvalidMarkException;

public class Student {
	private String fullName;
	private Map<Subject, Integer> subjectMarks;
	
	public Student(String fullName, Map<Subject, Integer> subjectMarks) throws InvalidMarkException, NoSubjectsException {
		if (subjectMarks == null || subjectMarks.isEmpty()) {
			throw new NoSubjectsException(fullName);
		}
		for (Integer subjectMark : subjectMarks.values()) {
			if (subjectMark < 0 || subjectMark > 10) {
				throw new InvalidMarkException(fullName, subjectMark);
			}
		}
		this.fullName = fullName;
		this.subjectMarks = subjectMarks;
	}

	public Student() {
		super();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Map<Subject, Integer> getSubjectMarks() {
		return subjectMarks;
	}

	public void setSubjectMarks(Map<Subject, Integer> subjectMarks) throws InvalidMarkException, NoSubjectsException {
		if (subjectMarks.isEmpty()) {
			throw new NoSubjectsException(fullName);
		}
		for (Integer subjectMark : subjectMarks.values()) {
			if (subjectMark < 0 || subjectMark > 10) {
				throw new InvalidMarkException(fullName, subjectMark);
			}
		}
		this.subjectMarks = subjectMarks;
	}

	public void print() {
		System.out.println("   " + fullName + ";" + subjectMarks.toString());
	}
}
