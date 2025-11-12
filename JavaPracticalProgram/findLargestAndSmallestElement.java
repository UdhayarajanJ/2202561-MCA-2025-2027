class FindMinMax {
    public static void main(String[] args) {
        int[] arr = { 12, 45, 9, 78, 23, 5 };
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("Largest element: " + max);
        System.out.println("Smallest element: " + min);
    }
}