package com.epam.auto.test.level2.claasses.univercities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.auto.test.level2.enums.Subject;
import com.epam.auto.test.level2.exceptions.InvalidDataException;

public class Student {
	private String fullName;
	private Map<Subject, Integer> subjectMarks;
	
	public Student(String fullName, Map<Subject, Integer> subjectMarks) throws InvalidDataException {
		this.fullName = fullName;
		this.subjectMarks = new HashMap<Subject, Integer>();
		if (subjectMarks != null && subjectMarks.size() != 0) {
			for (Entry<Subject, Integer> subjectMark : subjectMarks.entrySet()) {
				if (subjectMark.getValue() < 0) {
					throw new InvalidDataException(fullName + " has the mark less than 0: ", subjectMark.getValue().toString());
				} else if (subjectMark.getValue() > 10) {
					throw new InvalidDataException(fullName + " has the mark more than 10: ", subjectMark.getValue().toString());
				} else {
					this.subjectMarks.put(subjectMark.getKey(), subjectMark.getValue());
				}
			}
		} else {
			throw new InvalidDataException(fullName + " has no subjects: ");
		}
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

	public void setSubjectMarks(Map<Subject, Integer> subjectMarks) throws InvalidDataException {
		if (subjectMarks != null && subjectMarks.size() != 0) {
			for (Entry<Subject, Integer> subjectMark : subjectMarks.entrySet()) {
				if (subjectMark.getValue() < 0) {
					throw new InvalidDataException(fullName 
							+ " has the mark less than 0: ", subjectMark.getValue().toString());
				} else if (subjectMark.getValue() > 10) {
					throw new InvalidDataException(fullName 
							+ " has the mark more than 10: ", subjectMark.getValue().toString());
				} else {
					this.subjectMarks = subjectMarks;
				}
			}
		} else {
			throw new InvalidDataException(fullName + " has no subjects: ");
		}
	}

	public void print() {
		System.out.println("   " + fullName + ";" + subjectMarks.toString());
	}
}
