import MyMathFunctions.MathUtils;

public class MainApp {
    public static void main(String[] args) {
        double num = 25.0;
        double base = 2.0;
        double exponent = 3.0;
        // Use the methods from the imported package class
        double sqrtResult = MathUtils.findSqrt(num);
        double powerResult = MathUtils.findPower(base, exponent);
        System.out.println("The square root of " + num + " is: " + sqrtResult);
        System.out.println(base + " raised to the power of " + exponent + " is: " + powerResult);
    }
}