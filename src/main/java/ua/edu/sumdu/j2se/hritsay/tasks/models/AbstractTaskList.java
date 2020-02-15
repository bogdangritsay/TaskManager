package ua.edu.sumdu.j2se.hritsay.tasks.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;


public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    /**
     * Method to remove specific task
     * @param task it's  specific task
     * @return true if the task has been deleted
     * return false if the task hasn't been deleted
     */
    public abstract boolean remove(Task task);

    /**
     * Method to remove task by id
     * @param id it's  id of the task
     * @return true if the task has been deleted
     * return false if the task hasn't been deleted
     */
    public abstract boolean remove(int id);

    /**
     * Method to add new task in list
     *
     * @param task it's task to add
     */
    public abstract void add(Task task);

    /**
     * Method to get size of the list
     * @return size of the list
     */
    public abstract int size();

    /**
     * method to get stream from list
     * @return task list in stream view
     */
    public abstract Stream<Task> getStream();

    /**
     * Method to get task by list index
     * @param index it's list index of the task
     * @return task by the index
     * return null if the task was not found
     * @throws IndexOutOfBoundsException if the index goes beyond the boundaries of the array
     */
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /**
     * Mathod to replace a task with the specified index with a task
     * @param index of the task to replace
     * @param task replacement for task
     */
    public abstract void replace(int index, Task task);
}
