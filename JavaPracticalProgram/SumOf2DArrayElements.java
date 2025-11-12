public class SumOf2DArrayElements {
    public static void main(String[] args) {
        int[][] numbers = {
                { 10, 20, 30 },
                { 40, 50, 60 }
        };
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                sum += numbers[i][j];
            }
        }
        System.out.println("Sum of all elements: " + sum);
    }
}