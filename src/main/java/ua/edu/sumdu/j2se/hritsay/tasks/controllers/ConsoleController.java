package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hritsay.tasks.views.ConsoleView;
import ua.edu.sumdu.j2se.hritsay.tasks.views.View;
import ua.edu.sumdu.j2se.hritsay.tasks.models.AbstractTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.models.ArrayTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;
import ua.edu.sumdu.j2se.hritsay.tasks.models.TaskIO;

import java.io.File;

public class ConsoleController implements Controller {
    private View view;
    private AbstractTaskList taskList = new ArrayTaskList();
    final static Logger logger = Logger.getLogger(ConsoleController.class);

    public ConsoleController(View view) {
        this.view = view;
    }

    @Override
    public AbstractTaskList getTaskList() {
        return taskList;
    }

    @Override
    public void start() {
        try {
            TaskIO.readText(taskList, new File(Consts.TASKS_FILE));
            logger.info("The job list was read from a file.");
        } catch (NullPointerException e) {
            logger.info("New task list was been created.");
        }
        view.hello();
        process();
        logger.info("The program started its work.");
    }

    @Override
    public void process() {
        ConsoleNotification notification = new ConsoleNotification();
        Thread thread = new Thread(notification.getNotifySubSystem());
        thread.start();
        for(;;) {
            mainController();
        }
    }

    @Override
    public void showListController() {
        view.showListView(taskList);
        logger.info("The list of tasks is displayed.");
    }

    @Override
    public void addTaskController() {
        logger.info("Adding a task...");
       taskList.add(view.addTaskView(taskList));
        logger.info("Task was added.");
    }

    @Override
    public void removeTaskController() {
        int idRemTask = view.removeTaskView(taskList);
        logger.info("Removing a task...");
        for (Task task : taskList) {
            if(idRemTask == task.getTaskId()) {
                taskList.remove(task);
                logger.info("Task has been deleted.");
            }
        }
    }

    @Override
    public void editController() {
        logger.info("Editing a task...");
     int i =  view.readI(taskList);
       taskList.replace(i, view.editView(taskList, i));
        logger.info("Task was edited.");
    }

    @Override
    public void calendarController() {
    view.calendarView(taskList);
    logger.info("The calendar of tasks is displayed.");
    }

    @Override
    public void mainController() {
        int act = view.mainMenuView();
        switch (act) {
            case SHOW_LIST_ACTION:
                showListController();
                break;

            case ADD_TASK_ACTION:
                addTaskController();
                break;

            case REMOVE_TASK_ACTION:
                removeTaskController();
                break;

            case CALENDAR_TASK_ACTION:
                calendarController();
                break;

            case EDIT_TASK_ACTION:
                editController();
                break;

            case FINISH_ACTION:
                logger.info("Exit");
                System.exit(0);
                break;
        }
        TaskIO.writeText(taskList, new File(Consts.TASKS_FILE));
    }



}
