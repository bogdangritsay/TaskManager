package ua.edu.sumdu.j2se.hritsay.tasks;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, IOException {
		Task task1 = new Task("Покушать", 			LocalDateTime.of(2019, 2, 8, 13, 56, 59));
		Task task2 = new Task("Тренировка", 		LocalDateTime.of(2021, 3, 19, 14, 23, 42));
		Task task3 = new Task("Курсы", 			LocalDateTime.of(2020, 5, 4, 0, 48, 22));
		Task task4 = new Task("Выгулять собаку",	LocalDateTime.of(2019, 11, 23, 1, 34, 34));
		ArrayTaskList taskArrayList = new ArrayTaskList();
		taskArrayList.add(task1);
		taskArrayList.add(task2);
		taskArrayList.add(task3);
		taskArrayList.add(task4);

		System.out.println("GSON");

		File jsonOut = new File("JsonOut.json");
		//TaskIO.writeText(taskArrayList, jsonOut);

		TaskIO.write(taskArrayList, new FileWriter("JsonOut.json"));
		//TaskIO.read(actual, new FileReader("test.json"));


		AbstractTaskList tasksArray2 = new ArrayTaskList();
		/*TaskIO.readText(tasksArray2, new FileWriter("JsonOut.json"));*/
		System.out.println(tasksArray2);

		 /*
		System.out.println("Тестирование клонирование на двусвязном списке. ");
		LinkedTaskList linkedTaskList = new LinkedTaskList();
		linkedTaskList.add(task1);
		linkedTaskList.add(task2);
		linkedTaskList.add(task3);
		linkedTaskList.add(task4);
		Stream myStr = linkedTaskList.getStream();
		if(myStr == null) System.out.println("There is null 2");
        System.out.println(myStr);
        System.out.println(myStr.collect(Collectors.toList()));
		System.out.println("1-ый до клонирования.");
		System.out.println(linkedTaskList);
		System.out.println("Клонирование 1-го во 2-ой ... ");
		LinkedTaskList linkedTaskList2 = linkedTaskList.clone();
		System.out.println("Склонировано! \n Просмотр \n Исходный первый: \n"
		+ linkedTaskList + "\n Склонированый второй \n" + linkedTaskList2);
		System.out.println("Производим изменение второго склонированого на время = 33 и исходного = 33 ...");
		linkedTaskList.getTask(0).setTime(70);
		linkedTaskList2.getTask(0).setTime(33);
		System.out.println("Проверяем оба:");
		System.out.println("Просмотр \n первый: \n"
				+ linkedTaskList + "\n  второй \n" + linkedTaskList2);*/
	}
}
