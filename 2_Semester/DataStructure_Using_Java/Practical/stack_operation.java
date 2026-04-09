// Java Program to Implement Stack Operations (Push, Pop, Display)

import java.util.Scanner;

public class stack_operation {

    int stack[];
    int top = -1;
    int size;

    stack_operation(int size) {
        this.size = size;
        stack = new int[size];
    }

    void push(int value) {
        if (top == size - 1) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            stack[top] = value;
            System.out.println(value + " pushed into stack");
        }
    }

    void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
        } else {
            System.out.println(stack[top] + " popped from stack");
            top--;
        }
    }

    void display() {
        if (top == -1) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Stack elements:");
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter stack size: ");
        int size = sc.nextInt();

        stack_operation s = new stack_operation(size);
        while (true) {

            System.out.println("\n1. Push\n2. Pop\n3. Display\n4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter value: ");
                    int val = sc.nextInt();
                    s.push(val);
                    break;

                case 2:
                    s.pop();
                    break;

                case 3:
                    s.display();
                    break;

                case 4:
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }        
    }
}