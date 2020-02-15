package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;

public interface Notification {
    /**
     * Method to generate and draw notification message
     * @param task it's task for notification message
     */
    void notifyMessage(Task task);
}
