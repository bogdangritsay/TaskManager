package ua.edu.sumdu.j2se.hritsay.tasks.models;

public class Node implements Cloneable {
        Task item;
        Node next;
        Node prev;

        public Node(Node prev, Task item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{item=" + item + "}";
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
            Node node = (Node)super.clone();
            node.item = item.clone();
            return node;
    }

    public boolean hasNext() {
            if(next != null) return true;
            return false;
    }
}
