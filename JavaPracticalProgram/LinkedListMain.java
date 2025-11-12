import java.util.LinkedList;

public class LinkedListMain {
    public static void main(String[] args) {
        // Creating LinkedList
        LinkedList<String> gfg = new LinkedList<String>();
        // Adding values
        gfg.add("India");
        gfg.add("USA");
        gfg.add("U.K.");
        System.out.println("LinkedList Elements : ");
        for (int i = 0; i < gfg.size(); i++) {
            // get(i) returns element present at index i
            System.out.println("Element at index " + i
                    + " is: " + gfg.get(i));
        }
    }
}
