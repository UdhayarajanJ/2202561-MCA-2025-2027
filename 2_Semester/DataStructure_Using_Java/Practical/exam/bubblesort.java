package exam;

public class bubblesort {
    public static void main(String[] args) {
        int[] arr = new int[] { 10, 3, 4, 2 };

        // Bubble sort
        // for (int i = 0; i < arr.length - 1; i++) {
        // for (int j = 0; j < arr.length - i - 1; j++) {
        // if (arr[j] > arr[j + 1]) {
        // int temp = arr[j];
        // arr[j] = arr[j + 1];
        // arr[j + 1] = temp;
        // }
        // }
        // }

        // Selection Sort
        // for (int i = 0; i < arr.length - 1; i++) {
        // int midIndex = i;
        // for (int j = i + 1; j < arr.length; j++) {
        // if (arr[j] < arr[midIndex]) {
        // midIndex = j;
        // }
        // }

        // int temp = arr[i];
        // arr[i] = arr[midIndex];
        // arr[midIndex] = temp;
        // }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 3
            int j = i - 1; // 0
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
