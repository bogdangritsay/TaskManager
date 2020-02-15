package ua.edu.sumdu.j2se.hritsay.tasks.models;

/**
 * Class node for the linked list
 */
public class Node implements Cloneable {
    Task item;
    Node next;
    Node prev;

    /**
     * Constructor for the node
     * @param prev previous node
     * @param item item of new node
     * @param next next node
     */
    public Node(Node prev, Task item, Node next) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Ьethod for checking for the next node
     * @return true if the next node exists
     * return false if the next node not exists
     */
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node{item=" + item + "}";
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
        Node node = (Node) super.clone();
        node.item = item.clone();
        return node;
    }
}
