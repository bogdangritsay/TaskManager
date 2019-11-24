package ua.edu.sumdu.j2se.hritsay.tasks;
import java.util.Objects;
import java.util.Arrays;


public class ArrayTaskList {
    private int size;
    private int capacity;
    private Task[]taskArray = new Task[capacity];

    public boolean remove(Task task) {
        if (task!=null) {
            for (int i = 0; i < size; i++) {
                if (task.equals(taskArray[i])) {
                    int numMoved = size - 1 - i;
                    if (numMoved > 0)
                        System.arraycopy(taskArray, i + 1, taskArray, i,
                                numMoved);
                    taskArray[--size] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public void add(Task task) {
        if (size+1 >= capacity) {
            this.grow();
        }
        taskArray[size++] = task;
    }

    private void grow() {
        taskArray = Arrays.copyOf(taskArray, capacity + 1);
        capacity++;
    }

    public int size() {
        return size;
    }

	public Task getTask(int index) throws IndexOutOfBoundsException{
        if (index < size && index >= 0 && taskArray[index] != null) {
            return taskArray[index];
        } else {
                throw new IndexOutOfBoundsException("Index is incorrect!");
        }
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList tmpArrayList = new ArrayTaskList();
        for (Task i : taskArray) {
            if ((i.isRepeated())) {
                if ((i.nextTimeAfter(from) != -1)
                        && (i.nextTimeAfter(from) < to)) {
                    tmpArrayList.add(i);
            } else {
                    if ((i.getTime() > from) && (i.getTime() < to)) {
                        tmpArrayList.add(i);
                    }
                }
            }
        }
        return tmpArrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        return size == that.size &&
                capacity == that.capacity &&
                Arrays.equals(taskArray, that.taskArray);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity);
        result = 31 * result + Arrays.hashCode(taskArray);
        return result;
    }
}
