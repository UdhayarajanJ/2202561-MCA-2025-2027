import java.util.Scanner;
import java.util.StringTokenizer;

class StringApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. User Input and Initial Storage (String)
        System.out.print("Enter a sentence: ");
        String originalSentence = scanner.nextLine();
        System.out.println("Original Sentence: " + originalSentence);
        // 2. Sentence Modification (StringBuilder)
        StringBuilder modifiedSentenceBuilder = new StringBuilder(originalSentence);
        System.out.print("Enter text to append: ");
        String textToAppend = scanner.nextLine();
        modifiedSentenceBuilder.append(" ").append(textToAppend); // Append with a space
        System.out.println("Modified Sentence (appended): " + modifiedSentenceBuilder.toString());
        System.out.print("Enter text to insert at index 5: ");
        String textToInsert = scanner.nextLine();
        if (modifiedSentenceBuilder.length() >= 5) {
            modifiedSentenceBuilder.insert(5, " " + textToInsert + " "); // Insert with spaces
        } else {
            System.out.println("Sentence too short to insert at index 5.");
        }
        String finalModifiedSentence = modifiedSentenceBuilder.toString();
        System.out.println("Modified Sentence (inserted): " + finalModifiedSentence);
        // 3. Sentence Analysis (StringTokenizer)
        System.out.println("\nTokenizing the modified sentence:");
        // Delimiters: space, comma, period, etc.
        StringTokenizer tokenizer = new StringTokenizer(finalModifiedSentence, " .,!?;");
        int tokenCount = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            System.out.println("Token: " + token);
            tokenCount++;
        }
        System.out.println("Total tokens found: " + tokenCount);
        scanner.close();
    }
}