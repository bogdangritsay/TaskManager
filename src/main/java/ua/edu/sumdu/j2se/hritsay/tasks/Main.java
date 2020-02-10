package ua.edu.sumdu.j2se.hritsay.tasks;

import ua.edu.sumdu.j2se.hritsay.tasks.controllers.ConsoleController;
import ua.edu.sumdu.j2se.hritsay.tasks.controllers.Controller;

public class Main {

	public static void main(String[] args)  {
		Controller controller = new ConsoleController();
		controller.start();


	}
}
