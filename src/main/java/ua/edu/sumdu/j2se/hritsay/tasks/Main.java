package ua.edu.sumdu.j2se.hritsay.tasks;

public class Main {

	public static void main(String[] args)  {

		Controller controller = new ConsoleController();
		controller.start();
		for(;;) {
			controller.mainController();
		}
	}
}
