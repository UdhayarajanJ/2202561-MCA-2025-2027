// Define an interface for calculating the total amount
interface AmountCalculate {
    double calTotalAmount(double Price, int quantity);
}

// Implement the interface for a specific product type, e.g., a standard product
class Product implements AmountCalculate {
    public double calTotalAmount(double Price, int quantity) {
        return Price * quantity;
    }
}

// Main class to demonstrate the usage
public class ProductAmt {
    public static void main(String[] args) {
        // Calculate total amount for a standard product
        AmountCalculate p = new Product();
        double totalAmount = p.calTotalAmount(15.50, 5);
        System.out.println("Total amount for standard product: $" + String.format("%.2f", totalAmount));
    }
}