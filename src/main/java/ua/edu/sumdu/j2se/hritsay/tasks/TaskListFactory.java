package ua.edu.sumdu.j2se.hritsay.tasks;

import java.util.ArrayList;

public class TaskListFactory {
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
