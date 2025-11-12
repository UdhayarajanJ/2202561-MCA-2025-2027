class MainArray {
    public static void main(String[] args) {
        try {
            int[] numbers = { 1, 2, 3 };
            System.out.println(numbers[5]); // This will cause an ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds!");
            System.out.println("Exception message: " + e.getMessage());
        }
        System.out.println("Program continues after exception handling.");
    }
}