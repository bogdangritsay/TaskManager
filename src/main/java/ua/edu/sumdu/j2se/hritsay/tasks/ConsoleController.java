package ua.edu.sumdu.j2se.hritsay.tasks;

import ua.edu.sumdu.j2se.hritsay.tasks.model.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class ConsoleController implements Controller {
    private View view = new ConsoleView();
    private AbstractTaskList taskList = new ArrayTaskList();

    @Override
    public boolean canProcess(int act) {
        return false;
    }

    @Override
    public int process(AbstractTaskList taskList) {
        return FINISH_ACTION;
    }

    @Override
    public void showListController() {
        view.showListView(taskList);
    }

    @Override
    public void addTaskController() {
       taskList.add(view.addTaskView(taskList));
    }

    @Override
    public void removeTaskController() {

        int taskId = 0;
        boolean isIt = false;
        int count = 0;
        while (!isIt) {
            taskId = view.removeTaskView(taskList);
            for(Task task : taskList) {
                if(task.getTaskId() == taskId)  {
                    count = 1;
                    isIt = true;
                    break;
                }
            }
            if(count == 0) {
                view.notFound();
            }
        }
        taskList.remove(taskId);
    }

    @Override
    public void editController() {
     int i =  view.readId(taskList);
       taskList.replace(i, view.editView(taskList, i));
    }


    @Override
    public void calendarController() {
    view.calendarView(taskList);
    }

    public void start() {
        try {
            TaskIO.readText(taskList, new File("tasklist.json"));
        } catch (NullPointerException e) {
            System.out.println("New task list was been created;");
        }
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
                int save = view.confirmSaving();
                if(save == 1) {
                  TaskIO.writeText(taskList, new File("tasklist.json"));
                }
                System.exit(0);
                break;
        }
    }
}