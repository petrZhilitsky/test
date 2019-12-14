package com.epam.auto.test.level1.claasses.univercities;

import java.util.ArrayList;
import java.util.List;

import com.epam.auto.test.level1.exceptions.InvalidDataException;

public class Group {
	private String name;
	private List<Student> students = new ArrayList<>();
	
	public Group(String name, List<Student> students) throws InvalidDataException {
		this.name = name;
		if (students != null && students.size() != 0) {
			this.students = students;
		} else {
			throw new InvalidDataException(name + " group has no students: ");
		}
	}
	
	public Group() {
		super();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) throws InvalidDataException {
		if (students != null && students.size() != 0) {
			this.students = students;
		} else {
			throw new InvalidDataException(name + " group has no students: ");
		}
	}

	public void print() {
		System.out.println("  " + name + ":");
		for (Student student : students) {
			student.print();
		}
	}
	
}
