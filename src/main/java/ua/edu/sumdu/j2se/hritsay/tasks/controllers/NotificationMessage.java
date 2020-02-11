package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;
import java.time.LocalDateTime;

public class NotificationMessage {
    Task taskForNotify;
    LocalDateTime nextTimeActual;

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
