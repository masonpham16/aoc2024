import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class prob2_1 {
	// Class-level variable to keep track of the "safe number"
	private static int safeNumber = 0;

	public static void main(String[] args) {
		// Call the method to read and process the file content
		readFileAndPrint("input.txt");

		// Display the final safe number count
		System.out.println("Total Safe Sequences: " + getSafeNumber());
	}

	// Method to read a file and print each line
	public static void readFileAndPrint(String fileName) {
		Path path = Paths.get(fileName); // Create a Path object from the file name

		try {
			// Reads all lines from the file and stores them in a List of Strings
			List<String> lines = Files.readAllLines(path);
			for (String line : lines) {
				ArrayList<Integer> addy = makeList(line); // Process each line
				String result = safety(addy); // Check if the sequence is "Safe" or "Unsafe"
				System.out.println("Sequence: " + addy + " is " + result);
				if (result.equals("Safe")) {
					incrementSafeNumber(); // Increment safe count if the sequence is safe
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle any exceptions, such as file not found
		}
	}

	// Method to process the line, splitting it and converting parts to integers
	public static ArrayList<Integer> makeList(String line) {
		// Split the line by spaces
		String[] parts = line.split(" ");
		ArrayList<Integer> addy = new ArrayList<>();

		// Iterate through the parts and convert them to integers
		for (String part : parts) {
			try {
				// Trim whitespace and convert the string part to an integer
				addy.add(Integer.parseInt(part.trim()));
			} catch (NumberFormatException e) {
				System.out.println("Invalid number: " + part); // Handle invalid number format
			}
		}

		return addy; // Return the list of integers
	}

	// Method to determine if the list of integers is "Safe" or "Unsafe"
	public static String safety(ArrayList<Integer> address) {
		if (address.size() < 2) {
			return "Unsafe"; // Single-element or empty lists are unsafe
		}

		boolean ascending = address.get(1) > address.get(0); // Determine order from the first two elements

		for (int i = 1; i < address.size(); ++i) {
			int first = address.get(i - 1);
			int second = address.get(i);

			if (ascending) {
				// Check ascending order conditions
				if (second - first < 1 || second - first > 3) {
					return "Unsafe"; // Violates ascending conditions
				}
			} else {
				// Check descending order conditions
				if (first - second < 1 || first - second > 3) {
					return "Unsafe"; // Violates descending conditions
				}
			}
		}

		return "Safe"; // If all pairs satisfy the conditions, the list is "Safe"
	}

	// Increment the safe number count
	public static void incrementSafeNumber() {
		safeNumber++;
	}

	// Retrieve the safe number count
	public static int getSafeNumber() {
		return safeNumber;
	}
}
