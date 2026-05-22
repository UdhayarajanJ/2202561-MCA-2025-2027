// Java Program to Implement Binary Tree and Display Traversals

import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class binary_tree {

    Node root;

    // Insert node (level order style simple insertion)
    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {

        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else
            root.right = insertRec(root.right, data);

        return root;
    }

    // Inorder Traversal
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Preorder Traversal
    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder Traversal
    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        binary_tree tree = new binary_tree();

        // System.out.print("Enter number of nodes: ");
        // int n = sc.nextInt();

        // System.out.println("Enter node values:");
        int[] arr = new int[] { 45, 15, 79, 90, 10, 55, 12, 20, 50 };

        for (int i = 0; i < arr.length; i++) {
            tree.insert(arr[i]);
        }

        System.out.print("Inorder Traversal: ");
        tree.inorder(tree.root);

        System.out.print("\nPreorder Traversal: ");
        tree.preorder(tree.root);

        System.out.print("\nPostorder Traversal: ");
        tree.postorder(tree.root);

        sc.close();
    }
}