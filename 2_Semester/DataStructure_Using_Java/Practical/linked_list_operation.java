// Java Program to Create a Linked List and Display the Elements

import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class linked_list_operation {

    Node head = null;

    // Method to insert node at end
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
        }
    }

    // Method to display list
    void display() {

        if (head == null) {
            System.out.println("Linked List is empty");
        } else {

            Node temp = head;

            System.out.println("Linked List Elements:");

            while (temp != null) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }

            System.out.println("NULL");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        linked_list_operation list = new linked_list_operation();

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            list.insert(value);
        }

        list.display();

        sc.close();
    }
}