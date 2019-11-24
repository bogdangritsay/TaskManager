package ua.edu.sumdu.j2se.hritsay.tasks;

import java.lang.reflect.Array;

public class Main {

	public static void main(String[] args) {
		Task task1 = new Task("Eat !!!", 45);
		Task task2 = new Task("Eat !!!", 45);
		Task task3 = new Task("Кdfghjь", 56);
		Task task4 = new Task("Выгялятьть собаку", 93);
		ArrayTaskList taskArrayList = new ArrayTaskList();
		taskArrayList.add(task1);
		taskArrayList.add(task2);
		taskArrayList.add(task3);
		taskArrayList.add(task4);

		System.out.println(taskArrayList);

		taskArrayList.remove(task2);
		System.out.println(taskArrayList);

		System.out.println(taskArrayList.size());
	}
}
