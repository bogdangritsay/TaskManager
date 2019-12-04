package ua.edu.sumdu.j2se.hritsay.tasks;

public abstract class AbstractTaskList implements Iterable<Task>{

    public abstract boolean remove(Task task);

    public abstract void add(Task task);

    public abstract int size();

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public  AbstractTaskList incoming(int from, int to) {
        AbstractTaskList tmpAbstractTaskList;
        String type = this.getClass().toString();
        if (type.endsWith("ArrayTaskList")) {
            tmpAbstractTaskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        } else {
            tmpAbstractTaskList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        }
        int i = 0;
        while (this.size() > i) {
            if (this.getTask(i) != null) {
                if ((this.getTask(i).isRepeated())) {
                    if ((this.getTask(i).nextTimeAfter(from) != -1)

                            && (this.getTask(i).nextTimeAfter(from) < to)) {
                        tmpAbstractTaskList.add(this.getTask(i));
                    } else {
                        if ((this.getTask(i).getTime() > from) && (this.getTask(i).getTime() < to)) {
                            tmpAbstractTaskList.add(this.getTask(i));
                        }
                    }
                }
            }
            i++;
        }
        return tmpAbstractTaskList;
    }


}
