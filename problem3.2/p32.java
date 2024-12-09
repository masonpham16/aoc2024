import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p32 {
    private static int totalSum = 0;
    private static int switcher = 1;

    public static void main(String[] args) {
        readFile("input.txt");
        System.out.println("Total Sum: " + totalSum);
    }

    public static void readFile(String input) {
        try {
            Path path = Paths.get(input);
            List<String> lines = Files.readAllLines(path);

            // Patterns for different instructions
            String regexMul = "mul\\((\\d+),(\\d+)\\)";
            String regexDo = "do\\(\\)";
            String regexDont = "don't\\(\\)";

            Pattern patternMul = Pattern.compile(regexMul);
            Pattern patternDo = Pattern.compile(regexDo);
            Pattern patternDont = Pattern.compile(regexDont);

            for (String line : lines) {
                int position = 0;

                while (position < line.length()) {
                    // Match `do()` or `don't()`
                    Matcher matcherDo = patternDo.matcher(line.substring(position));
                    Matcher matcherDont = patternDont.matcher(line.substring(position));
                    Matcher matcherMul = patternMul.matcher(line.substring(position));

                    if (matcherDo.find() && matcherDo.start() == 0) {
                        System.out.println("Found do(), enabling additions.");
                        switcher = 1;
                        position += matcherDo.end();
                    } else if (matcherDont.find() && matcherDont.start() == 0) {
                        System.out.println("Found don't(), disabling additions.");
                        switcher = 0;
                        position += matcherDont.end();
                    } else if (matcherMul.find() && matcherMul.start() == 0) {
                        int firstInt = Integer.parseInt(matcherMul.group(1));
                        int secondInt = Integer.parseInt(matcherMul.group(2));
                        int product = firstInt * secondInt;

                        if (switcher == 1) {
                            System.out.println("Extracted: " + firstInt + ", " + secondInt + " ---> " + product);
                            totalSum += product;
                        } else {
                            System.out.println("Ignored: " + firstInt + ", " + secondInt);
                        }
                        position += matcherMul.end();
                    } else {
                        position++; // Skip unrecognized characters
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
