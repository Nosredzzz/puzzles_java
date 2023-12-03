package aoc23.day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay01 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day01/input.txt";

        int globalSum = 0;

        Map<String, Character> literalDigitsMap = new HashMap<>();
        literalDigitsMap.put("one", '1');
        literalDigitsMap.put("two", '2');
        literalDigitsMap.put("three", '3');
        literalDigitsMap.put("four", '4');
        literalDigitsMap.put("five", '5');
        literalDigitsMap.put("six", '6');
        literalDigitsMap.put("seven", '7');
        literalDigitsMap.put("eight", '8');
        literalDigitsMap.put("nine", '9');
        literalDigitsMap.put("zero", '0');

        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);

                Character firstDigit = null;
                Character lastDigit = null;

                // find the first and the last literal digit index in the line using the map
                int firstLiteralDigitIndex = line.length();
                int lastLiteralDigitIndex = -1;

                for (String literalDigit : literalDigitsMap.keySet()) {
                    int firstLocalIndex = line.indexOf(literalDigit);
                    int lastLocalIndex = line.lastIndexOf(literalDigit);
                    if (firstLocalIndex != -1) {
                        if (firstLocalIndex < firstLiteralDigitIndex) {
                            if (literalDigit != "zero") {
                                // do only if the literalDigit is different from zero
                                firstLiteralDigitIndex = firstLocalIndex;
                                firstDigit = literalDigitsMap.get(literalDigit);
                            }
                        }
                    }
                    if (lastLocalIndex != -1) {
                        if (lastLocalIndex > lastLiteralDigitIndex) {
                            lastLiteralDigitIndex = lastLocalIndex;
                            lastDigit = literalDigitsMap.get(literalDigit);
                        }
                    }
                }

                // find the first digit in the line
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i)) && (line.charAt(i) != '0')) {
                        if (i>firstLiteralDigitIndex) {
                            // the first digit is after the first literal digit.
                            // so we already have the first digit
                            break;
                        } // else we need to find the first digit
                        firstDigit = line.charAt(i);
                        break;
                    }
                }

                // find the last digit in the line
                for (int i = line.length() - 1; i >= 0; i--) {
                    if (Character.isDigit(line.charAt(i))) { // the last digit can be 0
                        if (i<lastLiteralDigitIndex) {
                            // the last digit is before the last literal digit.
                            // so we already have the last digit.
                            break;
                        } // else we need to find the last digit
                        lastDigit = line.charAt(i);
                        break;
                    }
                }

                int localNum = Integer.parseInt(firstDigit.toString() + lastDigit.toString());

                System.out.println("The number created by the first and last digit is: " + localNum);

                globalSum += localNum;

                if ((localNum < 10) || (localNum > 99)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The global sum is: " + globalSum);


    }

}
