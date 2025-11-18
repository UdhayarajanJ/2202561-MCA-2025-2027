class ExceptionHandling {
    public static void main(String[] args) {
        try {
            // ArithmeticException
            int res = 10 / 0;
            // NullPointerException
            String s = null;
            System.out.println(s.length());
        } catch (ArithmeticException e) {
            System.out.println(
                    "Caught ArithmeticException: " + e);
        } catch (NullPointerException e) {
            System.out.println(
                    "Caught NullPointerException: " + e);
        } finally {
            System.out.println("This code in the finally block always executes.");
        }
        System.out.println("Program continues after exception handling.");
    }
}