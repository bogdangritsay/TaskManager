package ua.edu.sumdu.j2se.hritsay.tasks.views;

import ua.edu.sumdu.j2se.hritsay.tasks.models.AbstractTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;

import java.time.LocalDateTime;

/**
 * View for the program
 */
public interface View {

    /**
     *
     * Method to draw menu for removing task
     * @param taskList task list of removing task
     * @return id of the removing task
     */
    int removeTaskView(AbstractTaskList taskList);

    /**
     * Method to draw menu for adding task
     * @param taskList task list of adding task
     * @return task it's to add
     */
    Task addTaskView(AbstractTaskList taskList);

    /**
     * Method to draw menu for editing task
     * @param taskList task list of editing task
     * @param i index of editing task
     * @return edited task
     */
    Task editView(AbstractTaskList taskList, int i);

    /**
     * Method to draw calendar
     * @param taskList task list for calendar
     */
    void calendarView(AbstractTaskList taskList);

    /**
     * Method to draw main menu
     * @return user selection action
     */
    int mainMenuView();

    /**
     * Method to draw all task list
     * @param taskList task list to draw
     */
    void showListView(AbstractTaskList taskList);

    /**
     * Method to draw reading id an returning index of this task
     * @param taskList task list to read
     * @return index of the task
     */
    int readI(AbstractTaskList taskList);

    /**
     * Method to draw reading date
     * @return date which has been read
     */
    LocalDateTime readDate();

    /**
     * Method to draw greeting
     */
    void hello();

}


