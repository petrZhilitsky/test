package com.epam.auto.test.level2.enums;

import java.util.ArrayList;
import java.util.List;

import com.epam.auto.test.level2.claasses.univercities.Group;
import com.epam.auto.test.level2.exceptions.InvalidDataException;

public class Faculty {
	private String name;
	private List<Group> groups = new ArrayList<>();
	
	public Faculty() {
		super();
	}

	public Faculty(String name, List<Group> groups) throws InvalidDataException {
		this.name = name;
		if (groups != null && groups.size() != 0) {
			this.groups = groups;
		} else {
			throw new InvalidDataException(name + " faculty has no groups: ");
		}
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
	
	public void setGroups(List<Group> groups) throws InvalidDataException {
		if (groups != null && groups.size() != 0) {
			this.groups = groups;
		} else {
			throw new InvalidDataException(name + " faculty has no groups: ");
		}
	}

	public void print() {
		System.out.println(" " + name + ":");
		for (Group group : groups) {
			group.print();
		}
	}
	
	
	/*public enum ATF {
		ICES,TRACTORS,ECONOMY;
		
		public static String getFacName() {
			return Faculty.ATF.class.getSimpleName();
		}
	}
	
	public enum FTUG {
		PACKING,CUSTOMS,MANAGEMENT;
		
		public static String getFacName() {
			return Faculty.FTUG.class.getSimpleName();
		}
	}
	
	public enum FTK {
		ROADS,BRIDGES,AUTOMATISATION;
		
		public static String getFacName() {
			return Faculty.FTK.class.getSimpleName();
		}
	}*/
}