package com.epam.auto.test.level2.classes.univercities;

import java.util.ArrayList;
import java.util.List;

import com.epam.auto.test.level2.exceptions.NoStudentsException;

public class Group {
	private String name;
	private List<Student> students = new ArrayList<>();

	public Group(String name, List<Student> students) throws NoStudentsException {
		if (students == null || students.isEmpty()) {
			throw new NoStudentsException(name);
		}
		this.name = name;
		this.students = students;
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
	
	public void setStudents(List<Student> students) throws NoStudentsException {
		if (students == null || students.isEmpty()) {
			throw new NoStudentsException(name);
		}
		this.students = students;
	}

	public void print() {
		System.out.println("  " + name + ":");
		for (Student student : students) {
			student.print();
		}
	}
	
}
