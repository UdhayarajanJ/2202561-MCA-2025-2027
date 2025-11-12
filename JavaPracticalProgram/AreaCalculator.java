// Abstract class representing a generic Shape
abstract class Shape {
    // Abstract method to calculate and print the area
    public abstract void calculateArea();
}

// Concrete class for Rectangle
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void calculateArea() {
        double area = length * width;
        System.out.println("Area of Rectangle: " + area);
    }
}

// Main class to test the program
public class AreaCalculator {
    public static void main(String[] args) {
        // Create instances of concrete shape classes
        Rectangle rectangle = new Rectangle(10, 5);
        // Call the calculateArea method for each shape
        rectangle.calculateArea();
    }
}