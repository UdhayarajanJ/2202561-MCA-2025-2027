import java.util.Scanner;

public class AverageCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of values: ");
        int count = scanner.nextInt();

        if (count <= 0) {
            System.out.println("Please enter a positive number of values.");
            scanner.close();
            return;
        }

        double sum = 0;
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter number " + i + ": ");
            double number = scanner.nextDouble();
            sum += number;
        }

        double average = sum / count;

        System.out.println("The sum of the numbers is: " + sum);
        System.out.println("The average of the numbers is: " + average);

        scanner.close();
    }
}