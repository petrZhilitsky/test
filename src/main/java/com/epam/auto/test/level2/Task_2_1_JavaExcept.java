package com.epam.auto.test.level2;

import com.epam.auto.test.level2.classes.univercities.*;
import com.epam.auto.test.level2.enums.Subject;
import com.epam.auto.test.level2.exceptions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Task_2_1_JavaExcept {

	public static void main(String[] args)
			throws InvalidMarkException, NoSubjectsException, NoStudentsException, NoGroupsException, NoFacultiesException {
		List<Student> studentsTractors = Arrays.asList(
		    new Student("ПяточкинАА", Map.of(Subject.MATH, 8, Subject.PHYSICS, 7, Subject.MECHANICS, 6)),
		    new Student("ПупкинББ", Map.of(Subject.MATH, 8, Subject.HISTORY, 9, Subject.MECHANICS, 7)),
		    new Student("ИвановаАА", Map.of(Subject.ECONOMY, 9, Subject.HISTORY, 6, Subject.ENGLISH, 8))
		);
		List<Student> studentsICEs = Arrays.asList(
		    new Student("ЕрмалаеваЕЕ", Map.of(Subject.PHYSICS, 8, Subject.METROLOGY, 9, Subject.MECHANICS, 7)),
		    new Student("КалюжныйЖЖ", Map.of(Subject.PHYSICS, 7, Subject.METROLOGY, 6, Subject.MECHANICS, 8)),
		    new Student("НагорныйУУ", Map.of(Subject.INFORMATICS, 6, Subject.PHYSICS, 9, Subject.MECHANICS, 5))
		);
		List<Student> studentsCustoms = Arrays.asList(
			new Student("ЖилицкийПЕ", Map.of(Subject.LAW, 7, Subject.INFORMATICS, 7, Subject.ENGLISH, 8)),
			new Student("БимаРР", Map.of(Subject.ECONOMY, 8, Subject.LAW, 6, Subject.HISTORY, 5)),
		    new Student("ИванцоваСС", Map.of(Subject.LAW, 7, Subject.STATISTICS, 8, Subject.ECONOMY, 9))
		);
		List<Student> studentsPacking = Arrays.asList(
		    new Student("ПавловичРР", Map.of(Subject.MECHANICS, 6, Subject.METROLOGY, 9, Subject.PHYSICS, 6)),
		    new Student("ИвановаОО", Map.of(Subject.MATH, 7, Subject.PHYSICS, 7, Subject.MECHANICS, 7)),
		    new Student("ЭдуардовичНН", Map.of(Subject.METROLOGY, 7, Subject.ECONOMY, 9, Subject.ENGLISH, 5))
		);
		List<Student> studentsRoads = Arrays.asList(
		    new Student("КравченкоСВ", Map.of(Subject.MATH, 6, Subject.INFORMATICS, 7, Subject.MECHANICS, 9)),
		    new Student("ПетренкоВС", Map.of(Subject.HISTORY, 5, Subject.ECONOMY, 8, Subject.MECHANICS, 7)),
		    new Student("СергеевАП", Map.of(Subject.ENGLISH, 5, Subject.MATH, 7, Subject.PHYSICS, 8))
		);
		List<Student> studentsBridges = Arrays.asList(
		       new Student("РожковРО", Map.of(Subject.METROLOGY, 4, Subject.INFORMATICS, 8, Subject.PHYSICS, 7)),
		       new Student("НиколаевОЛ", Map.of(Subject.ECONOMY, 6, Subject.INFORMATICS, 4, Subject.MATH, 5)),
		       new Student("УмаровУУ", Map.of(Subject.MATH, 4, Subject.HISTORY, 7, Subject.PHYSICS, 8))
		);

		List<Group> groupsATF = Arrays.asList(
			new Group("Tractors", studentsTractors),
			new Group("ICEs", studentsICEs)
		);
		List<Group> groupsFTUG = Arrays.asList(
			new Group("Customs", studentsCustoms),
			new Group("Packing", studentsPacking)
		);
		List<Group> groupsFTK = Arrays.asList(
			new Group("Roads", studentsRoads),
			new Group("Bridges", studentsBridges)
		);

		List<Faculty> facultiesBNTU = Arrays.asList(
			new Faculty("ATF", groupsATF),
			new Faculty("FTUG", groupsFTUG),
			new Faculty("FTK", groupsFTK)
		);

		University universityBNTU = new University("BNTU", facultiesBNTU);

		universityBNTU.print();

		System.out.printf("Average mark for ЖилицкийПЕ: %.2f\n", universityBNTU.getStudentAverageMark("ЖилицкийПЕ"));
		System.out.printf("Average mark for 'Law' subject, 'Customs' group, FTUG: %.2f\n",
				universityBNTU.getSubjectAverageMarkByGroup("Customs", Subject.LAW));
		System.out.printf("Average mark for 'Math' subject: %.2f\n", universityBNTU.getSubjectAverageMark(Subject.MATH));
	}
}