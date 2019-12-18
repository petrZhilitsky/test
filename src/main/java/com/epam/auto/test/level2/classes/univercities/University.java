package com.epam.auto.test.level2.classes.univercities;

import java.util.ArrayList;
import java.util.List;

import com.epam.auto.test.level2.enums.Subject;
import com.epam.auto.test.level2.exceptions.NoFacultiesException;
import com.epam.auto.test.level2.exceptions.NoStudentsException;

public class University {
	private String name;
	private List<Faculty> faculties = new ArrayList<>();
	
	public University(String name, List<Faculty> faculties) throws NoFacultiesException {
		if (faculties == null || faculties.isEmpty()) {
			throw new NoFacultiesException(name);
		}
		this.name = name;
		this.faculties = faculties;
	}

	public University() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) throws NoFacultiesException {
		if (faculties == null || faculties.isEmpty()) {
			throw new NoFacultiesException(name);
		}
		this.faculties = faculties;
	}
	
	public void print() {
		System.out.println(name);
		for (Faculty faculty : faculties) {
			faculty.print();
		}
	}

	private Student getStudentByName(String fullName) {
		Student studentByName = null;
		List<Student> allStudents = this.getAllStudents();
		for (Student student : allStudents) {
			if (student.getFullName().equalsIgnoreCase(fullName)) {
				studentByName = student;
				break;
			}
		}
		return studentByName;
	}

	private List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		for (Faculty faculty : faculties) {
			for (Group group : faculty.getGroups()) {
				students.addAll(group.getStudents());
			}
		}
		return students;
	}

	private List<Group> getAllGroups() {
		List<Group> allGroups = new ArrayList<>();
		for (Faculty faculty : faculties) {
			allGroups.addAll(faculty.getGroups());
		}
		return allGroups;
	}
	
	private List<Student> getStudentsByGroup(String groupName) throws NoStudentsException {
		List<Student> studentsByGroup = new ArrayList<>();
		List<Group> allGroups = getAllGroups();
		for (Group group : allGroups) {
			if (group.getName().equalsIgnoreCase(groupName)) {
				studentsByGroup.addAll(group.getStudents());
			}
		}
		if (studentsByGroup.isEmpty()) {
			throw new NoStudentsException(groupName);
		}
		return studentsByGroup;
	}
	
	public double getStudentAverageMark(String fullName) {
		Student student = this.getStudentByName(fullName);
		double averageMark;
		int sum = 0;
		if (student != null) {
			for (int mark : student.getSubjectMarks().values()) {
				sum += mark;
			}
			averageMark = (double) sum / student.getSubjectMarks().size();
		} else {
			averageMark = 0;
		}

		return averageMark;
	}
	
	public double getSubjectAverageMarkByGroup(String group, Subject subject) throws NoStudentsException {
		List<Student> getStudentsByGroup = this.getStudentsByGroup(group);
		int sum = 0;
		int count = 0;
		double averageMark;
		for (Student student : getStudentsByGroup) {
			sum += student.getSubjectMarks().get(subject);
			count++;
		}
		if(count != 0) {
			averageMark = (double) sum / count;
		} else {
			averageMark = 0;
		}
		return averageMark;
	}
	
	public double getSubjectAverageMark(Subject subject) {
		List<Student> students = this.getAllStudents();
		int sum = 0;
		int count = 0;
		double averageMark;
		for (Student student : students) {
			if (student.getSubjectMarks().get(subject) != null) {
				sum += student.getSubjectMarks().get(subject);
				count++;
			}
		}
		if(count != 0) {
			averageMark = (double) sum / count;
		} else {
			averageMark = 0;
		}
		return averageMark;
	}
}