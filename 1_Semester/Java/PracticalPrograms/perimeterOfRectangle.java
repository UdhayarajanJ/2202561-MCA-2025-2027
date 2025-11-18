import java.util.Scanner;

public class RectanglePerimeter {
    public static void main(String[] args) {

        double length;
        double width;
        double perimeter;


        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the length of the rectangle: ");
        length = scanner.nextDouble();


        System.out.print("Enter the width of the rectangle: ");
        width = scanner.nextDouble();


        perimeter = 2 * (length + width);


        System.out.println("The perimeter of the rectangle is: " + perimeter);


        scanner.close();
    }
}