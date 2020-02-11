package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import ua.edu.sumdu.j2se.hritsay.tasks.models.AbstractTaskList;

public interface Controller {
    int SHOW_LIST_ACTION = 1;
    int CALENDAR_TASK_ACTION = 2;
    int ADD_TASK_ACTION = 3;
    int REMOVE_TASK_ACTION = 4;
    int EDIT_TASK_ACTION = 5;
    int FINISH_ACTION = 0;

    void process() throws InterruptedException;

    void addTaskController();

    void removeTaskController();

    void editController();

    void calendarController();

    void mainController();

    void showListController();

    void start();

    AbstractTaskList getTaskList();
}
