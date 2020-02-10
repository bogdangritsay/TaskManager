package ua.edu.sumdu.j2se.hritsay.tasks.controllers;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.hritsay.tasks.models.AbstractTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.models.ArrayTaskList;
import ua.edu.sumdu.j2se.hritsay.tasks.models.Task;
import ua.edu.sumdu.j2se.hritsay.tasks.models.TaskIO;

import java.io.File;
import java.time.LocalDateTime;

public class ConsoleNotification implements Notification {
    private AbstractTaskList taskList = new ArrayTaskList();
    final static Logger logger = Logger.getLogger(ConsoleNotification.class);

    private Runnable notifySubSystem = new Runnable() {
        @Override
        public void run() throws NullPointerException {
            TaskIO.readText(taskList, new File(Consts.TASKS_FILE));
            while (true) {
                try {
                    for (Task task : taskList) {
                        notifyMessage(task);
                    }
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    logger.error("Interrupted exception in notifications.");
                }
            }
        }
    };

    public Runnable getNotifySubSystem() {
        return notifySubSystem;
    }

    @Override
    public void notifyMessage(Task task) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextPlus = now.plusMinutes(30);
        LocalDateTime nextTimeActual = task.nextTimeAfter(now);
        if (!(nextTimeActual == null)) {
            if ((nextTimeActual.isBefore(nextPlus))) {
                System.out.println("-------------------!!!NOTIFICATION!!!----------------------");
                System.out.println("Don't forget that today at: "
                        + nextTimeActual.getHour() + ":"
                        + nextTimeActual.getMinute()
                        + " you must: "
                        + task.getTitle());
                System.out.println("-----------------------------------------------------------");
            }
        }
    }
}






