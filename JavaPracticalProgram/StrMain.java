public class StrMain {
    public static void main(String[] args) {
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName; // Using '+' operator
        String fullMessage = "Welcome ".concat(fullName); // Using concat() method
        String s1 = "hello";
        String s2 = "hello";
        boolean isEqual1 = s1.equals(s2); // true
        String s = "This is a test.";
        System.out.println("Original: " + s);
        String upper = s.toUpperCase();
        String lower = s.toLowerCase();
        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
        System.out.println("Full name " + fullMessage);
        System.out.println("Equal or not " + isEqual1);
        System.out.println("Length of a string s = " + s);
    }
}