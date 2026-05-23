// Java implementation of Priority Queue using Linked List

class Node {
    int data;
    int priority;
    Node next;

    Node(int d, int p) {
        data = d;
        priority = p;
        next = null;
    }
}
public class PriorityQueue {
    // Create new node
    static Node newNode(int d, int p) {
        return new Node(d, p);
    }

    // Return value at head
    static int peek(Node head) {
        return head.data;
    }

    // Remove highest priority element
    static Node pop(Node head) {
        return head.next;
    }

    // Insert according to priority (lower value = higher priority)
    static Node push(Node head, int d, int p) {
        Node temp = newNode(d, p);

        // Insert at beginning
        if (head == null || head.priority > p) {
            temp.next = head;
            head = temp;
        } else {
            Node current = head;
            while (current.next != null && current.next.priority <= p) {
                current = current.next;
            }
            temp.next = current.next;
            current.next = temp;
        }
        return head;
    }

    // Check if empty
    static boolean isEmpty(Node head) {
        return head == null;
    }

    // Driver code
    public static void main(String[] args) {
        Node pq = newNode(4, 1);
        pq = push(pq, 5, 2);
        pq = push(pq, 6, 3);
        pq = push(pq, 7, 0);

        while (!isEmpty(pq)) {
            System.out.println(peek(pq));
            pq = pop(pq);
        }
    }
}