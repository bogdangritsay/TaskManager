package ua.edu.sumdu.j2se.hritsay.tasks;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>{

    public abstract boolean remove(Task task);

    public abstract void add(Task task);

    public abstract int size();

    public abstract Stream<Task> getStream();

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public  final AbstractTaskList incoming(int from, int to) {
       AbstractTaskList tmpAbstractTaskList;
        String type = this.getClass().toString();
        if (type.endsWith("ArrayTaskList")) {
            tmpAbstractTaskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        } else {
            tmpAbstractTaskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        }

        this.getStream().filter(task -> task != null &&
                    task.nextTimeAfter(from) != -1 && task.nextTimeAfter(from) < to).forEach(tmpAbstractTaskList::add);
        return tmpAbstractTaskList;
    }


    @Override
    public Iterator<Task> iterator() {
        return null;
    }

}
