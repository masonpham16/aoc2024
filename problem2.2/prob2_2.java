import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class prob2_2 {
	private static int safeNumber = 0;

	public static void main(String[] args) {
		readFileAndPrint("input.txt");
		System.out.println("Total Safe Sequences: " + getSafeNumber());
	}

	public static void readFileAndPrint(String fileName) {
		Path path = Paths.get(fileName);

		try {
			List<String> lines = Files.readAllLines(path);
			for (String line : lines) {
				ArrayList<Integer> addy = makeList(line);
				String result = safety(addy);

				// If not safe, check if removing one level makes it safe
				if (result.equals("Unsafe") && canBeMadeSafeByRemovingOne(addy)) {
					result = "Safe"; // Mark as safe if one level removal works
				}

				System.out.println("Sequence: " + addy + " is " + result);
				if (result.equals("Safe")) {
					incrementSafeNumber();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Integer> makeList(String line) {
		String[] parts = line.split(" ");
		ArrayList<Integer> addy = new ArrayList<>();

		for (String part : parts) {
			try {
				addy.add(Integer.parseInt(part.trim()));
			} catch (NumberFormatException e) {
				System.out.println("Invalid number: " + part);
			}
		}

		return addy;
	}

	public static String safety(ArrayList<Integer> address) {
		if (address.size() < 2) {
			return "Unsafe";
		}

		boolean ascending = address.get(1) > address.get(0);

		for (int i = 1; i < address.size(); ++i) {
			int first = address.get(i - 1);
			int second = address.get(i);

			if (ascending) {
				if (second - first < 1 || second - first > 3) {
					return "Unsafe";
				}
			} else {
				if (first - second < 1 || first - second > 3) {
					return "Unsafe";
				}
			}
		}

		return "Safe";
	}

	public static boolean canBeMadeSafeByRemovingOne(ArrayList<Integer> address) {
		// Try removing each level one by one and check if the sequence becomes safe
		for (int i = 0; i < address.size(); i++) {
			ArrayList<Integer> modifiedAddress = new ArrayList<>(address);
			modifiedAddress.remove(i); // Remove the level at index i
			if (safety(modifiedAddress).equals("Safe")) {
				return true; // If removing the level results in a safe sequence, return true
			}
		}
		return false; // If no level removal makes the sequence safe, return false
	}

	public static void incrementSafeNumber() {
		safeNumber++;
	}

	public static int getSafeNumber() {
		return safeNumber;
	}
}
