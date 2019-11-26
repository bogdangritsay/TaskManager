package ua.edu.sumdu.j2se.hritsay.tasks;

public class LinkedTaskList extends AbstractTaskList {
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
            for(Node x = first; x != null; x = x.next) {
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

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    /*public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList tmpLinkedTaskList = new LinkedTaskList();
        for (Node i = first; i != null; i = i.next) {
            if ((i.item.isRepeated())) {
                if ((i.item.nextTimeAfter(from) != -1)
                        && (i.item.nextTimeAfter(from) < to)) {
                    tmpLinkedTaskList.add(i.item);
                } else {
                    if ((i.item.getTime() > from) && (i.item.getTime() < to)) {
                        tmpLinkedTaskList.add(i.item);
                    }
                }
            }
        }
        return tmpLinkedTaskList;
    }*/

    private Node getNode(int index) {
        if (size > 0 && index < size) {
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else if(size == 0) {
            System.out.println("List is empty");
            return null;
        } else {
            System.out.println("Incorrect index!");
            return null;
        }
    }



}
