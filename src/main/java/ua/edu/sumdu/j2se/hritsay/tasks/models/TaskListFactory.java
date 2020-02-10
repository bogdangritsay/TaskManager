package ua.edu.sumdu.j2se.hritsay.tasks.models;

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
