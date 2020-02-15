package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import ua.edu.sumdu.j2se.hritsay.tasks.models.AbstractTaskList;

public interface Controller {
    int SHOW_LIST_ACTION = 1;
    int CALENDAR_TASK_ACTION = 2;
    int ADD_TASK_ACTION = 3;
    int REMOVE_TASK_ACTION = 4;
    int EDIT_TASK_ACTION = 5;
    int FINISH_ACTION = 0;

    /**
     * Method to process program
     * @throws InterruptedException where thread is interrupt
     */
    void process() throws InterruptedException;

    /**
     * Method to control adding task
     */
    void addTaskController();

    /**
     * Method to control removing task
     */
    void removeTaskController();

    /**
     * Method to control editing task
     */
    void editController();

    /**
     * Method to control calendar
     */
    void calendarController();

    /**
     * Method to control main menu
     */
    void mainController();

    /**
     * Method to control showing list
     */
    void showListController();

    /**
     * Method to start program
     */
    void start();
}
