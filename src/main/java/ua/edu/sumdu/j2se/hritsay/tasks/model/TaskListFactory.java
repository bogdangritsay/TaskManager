package ua.edu.sumdu.j2se.hritsay.tasks.model;

public class TaskListFactory<Task> {
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        switch (type) {
            case ARRAY:
                return new ArrayTaskList();
            case LINKED:
                return new LinkedTaskList();
        }
        return null;
    }
}
