package ua.edu.sumdu.j2se.hritsay.tasks.models;

public class TaskListFactory<Task> {

    /**
     * Method to create task list with specific type
     * @param type type to create
     * @return created task list
     * return null if task list has not been created
     */
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
