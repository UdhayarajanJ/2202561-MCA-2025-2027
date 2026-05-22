package exam;

class treeNode {
    int data;
    treeNode left, right;

    treeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

public class binarytree {
    treeNode root;

    public treeNode insertRec(treeNode root, int data) {
        if (root == null) {
            root = new treeNode(data);
            return root;
        }

        else if (root.data > data) {
            root.left = insertRec(root.left, data);
            return root;
        }

        else if (root.data < data) {
            root.right = insertRec(root.right, data);
            return root;
        }

        return root;
    }

    public void inOrder(treeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data);
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 4, 1, 5, 3 };
        binarytree tree = new binarytree();
        for (int i = 0; i < arr.length; i++) {
            tree.root = tree.insertRec(tree.root, arr[i]);
        }

        tree.inOrder(tree.root);
    }
}
