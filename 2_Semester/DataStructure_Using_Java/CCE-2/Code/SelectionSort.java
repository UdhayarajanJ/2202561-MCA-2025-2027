public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = { 90, 34,12,67 ,22 };
        int n = arr.length;
        int temp;
        int minIndex;

        System.out.println("Before using selection sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        for (int i = 0; i < n - 1; i++) {

            minIndex = i;

            // Find the smallest element in remaining array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with first element
            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        System.out.println("After using selection sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
