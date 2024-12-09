import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.*;

public class p31 {
    // Global variable to hold the total sum
    private static int totalSum = 0;

    public static void main(String[] args) {
        System.out.println("Extracting integers from mul(int,int)");

        // Call the readFile method with a sample file
        readFile("sample.txt");

        // Print the total sum
        System.out.println("Total Sum: " + totalSum);
    }

    public static void readFile(String input) {
        try {
            // Get the file path
            Path path = Paths.get(input);

            // Read all lines from the file into a List of Strings
            List<String> lines = Files.readAllLines(path);

            // Define the regex pattern with capturing groups for the two integers
            String regex = "mul\\((\\d+),(\\d+)\\)";

            // Compile the pattern
            Pattern pattern = Pattern.compile(regex);

            // Iterate through each line in the file
            for (String line : lines) {
                // Create a matcher to find occurrences
                Matcher matcher = pattern.matcher(line);

                // Find all matches in the current line
                while (matcher.find()) {
                    // Retrieve the captured groups (the two integers)
                    int firstInt = Integer.parseInt(matcher.group(1)); // First integer
                    int secondInt = Integer.parseInt(matcher.group(2)); // Second integer
                    int multiple = firstInt * secondInt;

                    // Print the integers and their product
                    System.out.println("Extracted: " + firstInt + ", " + secondInt + " ---> " + multiple);

                    // Update the total sum
                    totalSum += multiple;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
