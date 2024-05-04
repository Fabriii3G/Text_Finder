package DataStructures;
import java.util.NoSuchElementException;
class Node {
    String document;
    int WordNumber;
    Node next;

    public Node(int value, String doc) {
        this.document = doc;
        this.WordNumber = value;
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
            if (current.WordNumber == element) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean add(int element, String doc) {
        Node newNode = new Node(element, doc);
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
        if (this.head.WordNumber == element) {
            this.head = this.head.next;
            this.size--;
            return element;
        }
        Node current = this.head;
        while (current.next != null) {
            if (current.next.WordNumber == element) {
                current.next = current.next.next;
                this.size--;
                return element;
            }
            current = current.next;
        }
        throw new NoSuchElementException();
    }
    public String getListAsString() {
        StringBuilder listString = new StringBuilder();
        Node current = head;
        while (current != null) {
            listString.append("WordNumber: ").append(current.WordNumber).append(", Document: ").append(current.document).append("\s");
            current = current.next;
        }
        return listString.toString();
    }
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println("WordNumber: " + current.WordNumber + ", Document: " + current.document);
            current = current.next;
        }
    }
}