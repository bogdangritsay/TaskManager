package ua.edu.sumdu.j2se.hritsay.tasks;

import java.time.LocalDateTime;

public interface View {

    public int removeTaskView(AbstractTaskList taskList);

    public Task addTaskView(AbstractTaskList taskList);

    public Task editView(AbstractTaskList taskList, int i);

    public void calendarView(AbstractTaskList taskList);

    public int mainMenuView();

    public void showListView(AbstractTaskList taskList);

    public int confirmSaving();

    public int readI(AbstractTaskList taskList);

    public LocalDateTime readDate();

    public void hello();

}


