package ua.edu.sumdu.j2se.hritsay.tasks;

import ua.edu.sumdu.j2se.hritsay.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.model.Task;

public interface View {

    public int removeTaskView(AbstractTaskList taskList);

    public Task addTaskView(AbstractTaskList taskList);

    public Task editView(AbstractTaskList taskList, int i);

    public void calendarView(AbstractTaskList taskList);

    public int mainMenuView();

    public void showListView(AbstractTaskList taskList);

    public void notFound();

    public int confirmSaving();

    public int readId(AbstractTaskList taskList);

}


