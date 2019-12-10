package ua.edu.sumdu.j2se.hritsay.tasks;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		Task task1 = new Task("Покушать", 45);
		Task task2 = new Task("Тренировка", 45);
		Task task3 = new Task("Курсы", 56);
		Task task4 = new Task("Выгулять собаку", 93);

		/*ArrayTaskList taskArrayList = new ArrayTaskList();
		taskArrayList.add(task1);
		taskArrayList.add(task2);
		taskArrayList.add(task3);
		taskArrayList.add(task4);
		System.out.println(taskArrayList);

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

		/*
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
