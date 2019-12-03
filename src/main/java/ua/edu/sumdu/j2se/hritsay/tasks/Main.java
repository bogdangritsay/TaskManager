package ua.edu.sumdu.j2se.hritsay.tasks;



public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		Task task1 = new Task("Покушать", 45);
		Task task2 = new Task("Тренировка", 45);
		Task task3 = new Task("Курсы", 56);
		Task task4 = new Task("Выгулять собаку", 93);
		ArrayTaskList taskArrayList = new ArrayTaskList();
		taskArrayList.add(task1);
		taskArrayList.add(task2);
		taskArrayList.add(task3);
		taskArrayList.add(task4);
		System.out.println(taskArrayList);


		LinkedTaskList linkedTaskList = new LinkedTaskList();
		linkedTaskList.add(task1);
		linkedTaskList.add(task2);
		linkedTaskList.add(task3);
		linkedTaskList.add(task4);

		System.out.println(linkedTaskList);

		LinkedTaskList linkedTaskList2 = linkedTaskList.clone();

		linkedTaskList2.getTask(2).setTime(70);

		System.out.println(linkedTaskList2);





	}
}
