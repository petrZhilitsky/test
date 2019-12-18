package com.epam.auto.test.level2.classes.univercities;

import java.util.ArrayList;
import java.util.List;

import com.epam.auto.test.level2.exceptions.NoGroupsException;

public class Faculty {
	private String name;
	private List<Group> groups = new ArrayList<>();
	
	public Faculty() {
		super();
	}

	public Faculty(String name, List<Group> groups) throws NoGroupsException {
		if (groups == null || groups.isEmpty()) {
			throw new NoGroupsException(name);
		}
		this.name = name;
		this.groups = groups;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) throws NoGroupsException {
		if (groups == null || groups.isEmpty()) {
			throw new NoGroupsException(name);
		}
		this.groups = groups;
	}

	public void print() {
		System.out.println(" " + name + ":");
		for (Group group : groups) {
			group.print();
		}
	}
}