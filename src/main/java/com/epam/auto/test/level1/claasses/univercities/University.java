package com.epam.auto.test.level1.claasses.univercities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.epam.auto.test.level1.enums.Faculty;
import com.epam.auto.test.level1.enums.Subject;
import com.epam.auto.test.level1.exceptions.InvalidDataException;

public class University {
	private String name;
	private List<Faculty> faculties = new ArrayList<>();
	
	public University(String name, List<Faculty> faculties) throws InvalidDataException {
		this.name = name;
		if (faculties != null && faculties.size() != 0) {
			this.faculties = faculties;
		} else {
			throw new InvalidDataException(name + " has no faculties: ");
		}
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

	public void setFaculties(List<Faculty> faculties) throws InvalidDataException {
		if (faculties != null && faculties.size() != 0) {
			this.faculties = faculties;
		} else {
			throw new InvalidDataException(name + " has no faculties: ");
		}
	}
	
	public void print() {
		System.out.println(name);
		for (Faculty faculty : faculties) {
			faculty.print();
		}
	}
	
	public Student getStudentByName(String fullName) {
		Student studentByName = null;
		for (Faculty faculty : faculties) {
			for (Group group : faculty.getGroups()) {
				for (Student student : group.getStudents()) {
					if (student.getFullName().equalsIgnoreCase(fullName)) {
						studentByName = student;
						break;
					}
				}
			}
		}
		return studentByName;
	}
	
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		for (Faculty faculty : faculties) {
			for (Group group : faculty.getGroups()) {
				for (Student student : group.getStudents()) {
					students.add(student);
				}
			}
		}
		return students;
	}
	
	public List<Student> getStudentsByFaculty(String facultyName) {
		List<Student> studentsByFaculty = new ArrayList<>();
		for (Faculty faculty : faculties) {
			if (faculty.getName().equalsIgnoreCase(facultyName)) {
				for (Group group : faculty.getGroups()) {
					for (Student student : group.getStudents()) {
						studentsByFaculty.add(student);
					}
				}
			}
		}
		return studentsByFaculty;
	}
	
	public List<Student> getStudentsByGroup(String facultyName, String groupName) 
			throws InvalidDataException {
		List<Student> studentsByGroup = new ArrayList<>();
		for (Faculty faculty : faculties) {
			if (faculty.getName().equalsIgnoreCase(facultyName)) {
				for (Group group : faculty.getGroups()) {
					if (group.getName().equalsIgnoreCase(groupName)) {
						for (Student student : group.getStudents()) {
							studentsByGroup.add(student);
						}
					}	
				}
			}
		}
		if (studentsByGroup.size() > 0) {
			return studentsByGroup;
		} else {
			throw new InvalidDataException(groupName + " group has no students: ");
		}
	}
	
	public double getStudentAverageMark(String fullName) {
		Student student = this.getStudentByName(fullName);
		int sum = 0;
		for (int mark : student.getSubjectMarks().values()) {
			sum += mark;
		}
		return (double) sum / student.getSubjectMarks().size();
	}
	
	public double getSubjectAverageMarkByGroup(String faculty, String group, Subject subject) 
			throws InvalidDataException {
		List<Student> getStudentsByGroup = this.getStudentsByGroup(faculty, group);
		int sum = 0;
		int count = 0;
		for (Student student : getStudentsByGroup) {
			for (Entry<Subject, Integer> subjectMark : student.getSubjectMarks().entrySet()) {
				if (subjectMark.getKey().toString().equalsIgnoreCase(subject.toString())) {
					sum += subjectMark.getValue();
					count++;
				}
			}
		}
		return (double) sum / count;
	}
	
	public double getSubjectAverageMark(Subject subject) {
		List<Student> students = this.getAllStudents();
		int sum = 0;
		int count = 0;
		for (Student student : students) {
			for (Entry<Subject, Integer> subjectMark : student.getSubjectMarks().entrySet()) {
				if (subjectMark.getKey().toString().equalsIgnoreCase(subject.toString())) {
					sum += subjectMark.getValue();
					count++;
				}
			}
		}
		return (double) sum / count;
	}
}