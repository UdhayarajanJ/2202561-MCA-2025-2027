import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        // Create a HashSet of Strings
        HashSet<String> fruits = new HashSet<>();
        // Add elements to the HashSet
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // Adding a duplicate, which will be ignored
        // Print the HashSet
        System.out.println("Fruits in HashSet: " + fruits);
        // Check if an element exists
        System.out.println("Contains 'Banana'? " + fruits.contains("Banana"));
        System.out.println("Contains 'Grape'? " + fruits.contains("Grape"));
        // Remove an element
        fruits.remove("Orange");
        System.out.println("Fruits after removing Orange: " + fruits);
        // Iterate through the HashSet
        System.out.println("Iterating through fruits:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        // Get the size of the HashSet
        System.out.println("Size of HashSet: " + fruits.size());
        // Clear all elements
        fruits.clear();
        System.out.println("Fruits after clearing: " + fruits);
    }
}
