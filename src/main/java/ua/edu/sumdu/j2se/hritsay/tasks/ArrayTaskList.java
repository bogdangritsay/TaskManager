package ua.edu.sumdu.j2se.hritsay.tasks;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Arrays;
import java.lang.String;


public class ArrayTaskList extends AbstractTaskList implements Cloneable, Iterable<Task> {
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

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList arrayTaskList = (ArrayTaskList)super.clone();
        arrayTaskList.taskArray = taskArray.clone();
        return arrayTaskList;
    }

    @Override
    public String toString() {
        return "ArrayTaskList " +
                "size = " + size +
                ", capacity = " + capacity +
                ", taskArray = \n" + Arrays.toString(taskArray);
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {
            private int count = size;
            private int currentIndex;
            private  int countNext;

            @Override
            public boolean hasNext() {
                return currentIndex < size && taskArray[currentIndex] != null;
            }

            @Override
            public Task next() {
                if (currentIndex < count) {
                    countNext++;
                    return taskArray[currentIndex++];
                } else {
                    countNext++;
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public void remove() {
                if(countNext == 0){
                    throw new IllegalStateException();
                } else {
                    ArrayTaskList.this.remove(taskArray[--currentIndex]);
                    count--;
                }
            }
        };
        return it;
    }
}
