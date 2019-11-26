package ua.edu.sumdu.j2se.hritsay.tasks;

public class Node {
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
}
