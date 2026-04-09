// Java Program to Implement Doubly Linked List and its Operations

import java.util.Scanner;

class Node {
    int data;
    Node prev, next;

    Node(int data) {
        this.data = data;
        prev = next = null;
    }
}

public class DoublyLinkedList {

    Node head = null;

    // Insert at end
    void insert(int data) {

        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {

            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
            newNode.prev = temp;
        }

        System.out.println(data + " inserted");
    }

    // Delete from beginning
    void delete() {

        if (head == null) {
            System.out.println("List is empty");
        } else {

            System.out.println(head.data + " deleted");
            head = head.next;

            if (head != null) {
                head.prev = null;
            }
        }
    }

    // Display list
    void display() {

        if (head == null) {
            System.out.println("List is empty");
        } else {

            Node temp = head;

            System.out.println("Doubly Linked List Elements:");

            while (temp != null) {
                System.out.print(temp.data + " <-> ");
                temp = temp.next;
            }

            System.out.println("NULL");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();

        while (true) {

            System.out.println("\n1. Insert\n2. Delete\n3. Display\n4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter value: ");
                    int val = sc.nextInt();
                    list.insert(val);
                    break;

                case 2:
                    list.delete();
                    break;

                case 3:
                    list.display();
                    break;

                case 4:
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}