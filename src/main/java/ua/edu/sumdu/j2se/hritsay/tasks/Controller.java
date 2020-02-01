package ua.edu.sumdu.j2se.hritsay.tasks;

import ua.edu.sumdu.j2se.hritsay.tasks.model.AbstractTaskList;

public interface Controller {
    public int MAIN_MENU_ACTION = 10;
    public int SHOW_LIST_ACTION = 1;
    public int CALENDAR_TASK_ACTION = 2;
    public int ADD_TASK_ACTION = 3;
    public int REMOVE_TASK_ACTION = 4;
    public int EDIT_TASK_ACTION = 5;
    public int FINISH_ACTION = 0;

    public boolean canProcess(int act);

    public int process(AbstractTaskList taskList);

    public void addTaskController();

    public void removeTaskController();

    public void editController();

    public void calendarController();

    public void mainController();

    public void showListController();

    public void start();


}
