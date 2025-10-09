public class DivisibilityCheck {
    public static void main(String[] args) {
        int number = 55;

        if (number % 6 == 0) {
            System.out.println(number + " is divisible by 6.");
        } else {
            System.out.println(number + " is not divisible by 6.");
        }

        if (number % 11 == 0) {
            System.out.println(number + " is divisible by 11.");
        } else {
            System.out.println(number + " is not divisible by 11.");
        }

        if ((number % 6 == 0) && (number % 11 == 0)) {
            System.out.println(number + " is divisible by both 6 and 11.");
        } else {
            System.out.println(number + " is not divisible by both 6 and 11.");
        }
    }
}