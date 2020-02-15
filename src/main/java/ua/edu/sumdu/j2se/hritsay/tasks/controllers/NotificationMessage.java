package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;
import java.time.LocalDateTime;

/**
 * Message for the notification.
 */
public class NotificationMessage {
    private Task taskForNotify;
    private LocalDateTime nextTimeActual;

    /**
     * Constructor for notification message
     * @param taskForNotify sets task for notify
     * @param nextTimeActual sets next time for notify
     */
    public NotificationMessage(Task taskForNotify, LocalDateTime nextTimeActual) {
        this.taskForNotify = taskForNotify;
        this.nextTimeActual = nextTimeActual;
    }

    @Override
    public String toString() {
        return  "-------------------!!!NOTIFICATION!!!----------------------\n"
                +  "Don't forget that today at: "
                + nextTimeActual.getHour() + ":"
                + nextTimeActual.getMinute()
                + " you must: "
                + taskForNotify.getTitle()
                + "\n-----------------------------------------------------------";
    }
}
