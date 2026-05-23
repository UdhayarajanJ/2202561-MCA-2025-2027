import java.util.Scanner;

public class linear_binary_search {

    // Linear Search Method
    public static int linearSearch(int arr[], int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i; // element found
            }
        }
        return -1; // element not found
    }

    // Binary Search Method
    public static int binarySearch(int arr[], int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key) {
                return mid; // element found
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // element not found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter array elements (sorted for binary search):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();

        // Linear Search
        int result1 = linearSearch(arr, key);
        if (result1 != -1)
            System.out.println("Linear Search: Element found at index " + result1);
        else
            System.out.println("Linear Search: Element not found");

        // Binary Search
        int result2 = binarySearch(arr, key);
        if (result2 != -1)
            System.out.println("Binary Search: Element found at index " + result2);
        else
            System.out.println("Binary Search: Element not found");

        sc.close();
    }
}