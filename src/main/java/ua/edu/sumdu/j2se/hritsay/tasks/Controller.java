package ua.edu.sumdu.j2se.hritsay.tasks;

public interface Controller {
    public int SHOW_LIST_ACTION = 1;
    public int CALENDAR_TASK_ACTION = 2;
    public int ADD_TASK_ACTION = 3;
    public int REMOVE_TASK_ACTION = 4;
    public int EDIT_TASK_ACTION = 5;
    public int FINISH_ACTION = 0;

    public void process() throws InterruptedException;

    public void addTaskController();

    public void removeTaskController();

    public void editController();

    public void calendarController();

    public void mainController();

    public void showListController();

    public void start();

    public AbstractTaskList getTaskList();


}
