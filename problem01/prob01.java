import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class prob01 {
	public static void main(String[] args) {

		// Create two dynamic lists
		ArrayList<Integer> numOne = new ArrayList<>();
		ArrayList<Integer> numTwo = new ArrayList<>();

		// Create a Scanner to read the file (or console)
		Scanner scanner = new Scanner(System.in);

		// Read pairs of numbers
		while (scanner.hasNextInt()) {
			int first = scanner.nextInt(); // Read the first number
			int second = scanner.nextInt(); // Read the second number
			numOne.add(first); // Add to the list
			numTwo.add(second); // Add to the list
		}

		scanner.close();

		// Sort the list
		Collections.sort(numOne);
		Collections.sort(numTwo);

		// Create a HashMap to count duplicates
		HashMap<Integer, Integer> counts = new HashMap<>();

		// Iterate through numTwo and update counts
		for (int num : numTwo) {
			counts.put(num, counts.getOrDefault(num, 0) + 1);
		}

		int combined = 0;

		// Calculate the combined value based on the sorted lists
		for (int i = 0; i < numOne.size(); ++i) {
			combined += counts.getOrDefault(numOne.get(i), 0) * numOne.get(i);
		}

		// Output the result
		System.out.println(combined);
	}
}
