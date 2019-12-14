package com.epam.auto.test.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Task_04_05_JavaColl {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 20; i++) {
            int number = random.nextInt(41) - 20;
            list.add(number);
        }
        System.out.println(list);
        Collections.sort(list);
        List<Integer> result = list.stream().sorted((o1, o2) -> o2).collect(Collectors.toList());
		System.out.println(result);
	}
}