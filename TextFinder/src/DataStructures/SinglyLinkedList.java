package DataStructures;
import java.util.NoSuchElementException;
class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

public class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(int element) {
        Node current = this.head;
        while (current != null) {
            if (current.value == element) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean add(int element) {
        Node newNode = new Node(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
        return true;
    }

    public int remove(int element) {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        if (this.head.value == element) {
            this.head = this.head.next;
            this.size--;
            return element;
        }
        Node current = this.head;
        while (current.next != null) {
            if (current.next.value == element) {
                current.next = current.next.next;
                this.size--;
                return element;
            }
            current = current.next;
        }
        throw new NoSuchElementException();
    }
}