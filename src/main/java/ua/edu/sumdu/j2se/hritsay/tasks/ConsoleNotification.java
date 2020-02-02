package ua.edu.sumdu.j2se.hritsay.tasks;

public class ConsoleNotification implements Notification {
    @Override
    public void notify(AbstractTaskList taskList) {
        System.out.println("До таски: ");
    }

    Runnable task = new Runnable() {
        @Override
        public void run() {

        }
    };
    /*
    Thread thread = new Thread(task);
        thread.start();*/
}



