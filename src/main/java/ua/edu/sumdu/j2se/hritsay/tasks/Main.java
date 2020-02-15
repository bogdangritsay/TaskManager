package ua.edu.sumdu.j2se.hritsay.tasks;

import ua.edu.sumdu.j2se.hritsay.tasks.controllers.*;
import ua.edu.sumdu.j2se.hritsay.tasks.views.*;


public class Main {
	/**
	 * Method to launch program
	 * @param args args
	 */
	public static void main(String[] args)  {
		View view = new ConsoleView();
		Controller controller = new ConsoleController(view);
		controller.start();
	}
}
