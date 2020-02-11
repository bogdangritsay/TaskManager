package ua.edu.sumdu.j2se.hritsay.tasks.views;

import ua.edu.sumdu.j2se.hritsay.tasks.models.AbstractTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;

import java.time.LocalDateTime;

public interface View {

    int removeTaskView(AbstractTaskList taskList);

    Task addTaskView(AbstractTaskList taskList);

    Task editView(AbstractTaskList taskList, int i);

    void calendarView(AbstractTaskList taskList);

    int mainMenuView();

    void showListView(AbstractTaskList taskList);

    int readI(AbstractTaskList taskList);

    LocalDateTime readDate();

    void hello();

}


