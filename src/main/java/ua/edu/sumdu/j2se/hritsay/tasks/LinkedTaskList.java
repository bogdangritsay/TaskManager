package ua.edu.sumdu.j2se.hritsay.tasks;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Cloneable, Iterable<Task>  {
    private int size;
    private Node first;
    private Node last;

    public void add(Task task) {
        if (size == 0) {
            linkFirst(task);
        } else {
            linkLast(task);
        }
    }

    private void linkFirst(Task task) {
        Node f = first;
        Node newNode = new Node(null, task, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkLast(Task task) {
        Node l = last;
        Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean remove(Task task) {
        if (task == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (task.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }

        return false;
    }

    private void unlink(Node x) {
        Node next = x.next;
        Node prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is incorrect");
        }

        return getNode(index).item;
    }

    public int size() {
        return size;
    }

    @Override
    public Stream<Task> getStream() {
        ArrayTaskList tmp = new ArrayTaskList();
        for (int i = 0;  i < size; i++) {
            tmp.add(getTask(i));
        }
        return tmp.getStream();
    }


    private Node getNode(int index) {
        if (size > 0 && index < size) {
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else if (size == 0) {
            System.out.println("List is empty");
            return null;
        } else {
            System.out.println("Incorrect index!");
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        if (size != ((LinkedTaskList) o).size) return false;
        if (hashCode() != o.hashCode()) return false;
        for (int i = 0; i < size; i++) {
            if (!(getTask(i).equals(((LinkedTaskList) o).getTask(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Node i = first; i != last; i = i.next) {
            hashCode = 31 * hashCode + (i.item == null ? 0 : i.item.hashCode());
        }
        return hashCode;
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList linkedTaskList = (LinkedTaskList)super.clone();
        linkedTaskList.first = first.clone();
        linkedTaskList.last = last.clone();
        return linkedTaskList;
    }

    @Override
    public String toString() {
        String tasks = " ";
        for (int i = 0; i < size; i++) {
            tasks = tasks.concat(getTask(i).toString());
        }
        return "LinkedTaskList " +
                "size = " + size +
                ":  \n" + tasks;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            Node current = first;
            int countNext;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Task next() throws NoSuchElementException {
                if (hasNext()) {
                    Task data = current.item;
                    current = current.next;
                    countNext++;
                    return data;
                } else {
                    countNext++;
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public void remove() throws IllegalStateException {
                if(countNext == 0) {
                    throw new IllegalStateException();
                } else {
                    LinkedTaskList.this.remove(this.current.prev.item);
                }
            }
        };
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public void setLast(Node last) {
        this.last = last;
    }
}
