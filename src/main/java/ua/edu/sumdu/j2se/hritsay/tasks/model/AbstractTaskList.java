package ua.edu.sumdu.j2se.hritsay.tasks.model;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    public abstract boolean remove(Task task);

    public abstract boolean remove(int id);

    public abstract void add(Task task);

    public abstract int size();

    public abstract Stream<Task> getStream();

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public abstract void replace(int index, Task task);


}
