// Java Program to Implement Queue Operations (Insert, Delete, Display)

import java.util.Scanner;

class QueueOperation {

    int queue[];
    int front = -1, rear = -1;
    int size;

    QueueOperation(int size) {
        this.size = size;
        queue = new int[size];
    }

    void insert(int value) {

        if (rear == size - 1) {
            System.out.println("Queue Overflow");
        } else {

            if (front == -1)
                front = 0;

            rear++;
            queue[rear] = value;

            System.out.println(value + " inserted into queue");
        }
    }

    void delete() {

        if (front == -1 || front > rear) {
            System.out.println("Queue Underflow");
        } else {

            System.out.println(queue[front] + " deleted from queue");
            front++;
        }
    }

    void display() {

        if (front == -1 || front > rear) {
            System.out.println("Queue is empty");
        } else {

            System.out.println("Queue elements:");
            for (int i = front; i <= rear; i++) {
                System.out.println(queue[i]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter queue size: ");
        int size = sc.nextInt();

        QueueOperation q = new QueueOperation(size);

        while (true) {

            System.out.println("\n1. Insert\n2. Delete\n3. Display\n4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter value: ");
                    int val = sc.nextInt();
                    q.insert(val);
                    break;

                case 2:
                    q.delete();
                    break;

                case 3:
                    q.display();
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