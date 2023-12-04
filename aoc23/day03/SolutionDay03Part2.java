package aoc23.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDay03Part2 {

    public static void main(String[] args) {
        String inputFilePath = "aoc23/day03/input.txt";

        int globalPartNumbersSum = 0;

        List<List<Integer>> partNumbersMatrix = new ArrayList<>();

        // read the input file and create the matrix
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(lineNumber + ": " + line);
                // iterate over each character in the line
                List<Integer> currentLinePartNumbers = new ArrayList<>();
                // iterate over each character in the line in reverse order to calculate the part numbers
                int base10Multiplier = 1;
                for (int i = line.length() - 1; i >= 0; i--) {
                    char currentChar = line.charAt(i);

                    if (currentChar == '.') {
                        // considering '.' as -2
                        currentLinePartNumbers.add(-2);
                        base10Multiplier = 1;
                    }
                    // test if is numeric
                    else if (Character.isDigit(currentChar)) {
                        // considering the currentChar as a number
                        int currentCharNumber = Character.getNumericValue(currentChar);
                        int currentPartNumber = currentCharNumber * base10Multiplier;
                        currentLinePartNumbers.add(currentPartNumber);
                        System.out.println("currentPartNumber: " + currentPartNumber +
                                " base10Multiplier: " + base10Multiplier);
                        base10Multiplier *= 10;
                    }
                    // * is a special character, and we should consider it as -1
                    else if (currentChar == '*') {
                        currentLinePartNumbers.add(-1);
                        base10Multiplier = 1;
                    }
                    // anything else is a special character, and we should consider it as -2
                    else {
                        currentLinePartNumbers.add(-2);
                        base10Multiplier = 1;
                    }
                }
                partNumbersMatrix.add(currentLinePartNumbers);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // control list to verify the indexes around the current gear
        List<int[]> indexesToVerify = new ArrayList<>();
        indexesToVerify.add(new int[]{-1, -1});
        indexesToVerify.add(new int[]{-1, 0});
        indexesToVerify.add(new int[]{-1, 1});
        indexesToVerify.add(new int[]{0, -1});
        indexesToVerify.add(new int[]{0, 1});
        indexesToVerify.add(new int[]{1, -1});
        indexesToVerify.add(new int[]{1, 0});
        indexesToVerify.add(new int[]{1, 1});

        System.out.println("\n\n*** Verifying the matrix searching for valid gears:\n\n");
        // iterate over the matrix to find the part numbers
        for (int i = 0; i < partNumbersMatrix.size(); i++) {
            for (int j = 0; j < partNumbersMatrix.get(i).size(); j++) {
                int currentPartNumber = partNumbersMatrix.get(i).get(j);
                //System.out.print(currentPartNumber + " ");
                if (currentPartNumber == -1) {
                    // it is a former * symbol
                    ArrayList<Integer> numbersToAdd = new ArrayList<>();
                    for (int[] idxToVerify : indexesToVerify) {
                        System.out.println("Verifying: col=" + (i + idxToVerify[0]) + ", row=" + (j + idxToVerify[1]));
                        processNumberAroundGear(i + idxToVerify[0], j + idxToVerify[1],
                                partNumbersMatrix, numbersToAdd);
                    }
                    System.out.println("numbersToAdd size: " + numbersToAdd.size());
                    if (numbersToAdd.size() == 2) {
                        int firstNumberToAdd = numbersToAdd.get(0);
                        int secondNumberToAdd = numbersToAdd.get(1);
                        globalPartNumbersSum += firstNumberToAdd * secondNumberToAdd;
                    }
                }
            }
        }
        System.out.println("Final global sum: " + globalPartNumbersSum);
        }

    public static void processNumberAroundGear(int i, int j, List<List<Integer>> partNumbersMatrix, ArrayList<Integer> numbersToAdd) {
        // check if i and j are inside the matrix limits
        if (i < 0 || i >= partNumbersMatrix.size()) {
            return;
        }
        if (j < 0 || j >= partNumbersMatrix.get(i).size()) {
            return;
        }
        // i and j are inside the matrix limits

        int partialSum = 0;

        if (partNumbersMatrix.get(i).get(j) >= 0) {
            int digitIndex = j;
            ArrayList<Integer> digitsToVerify = new ArrayList<>();
            digitsToVerify.add(digitIndex);
            while (digitsToVerify.size() > 0){
                // update the digitIndex
                digitIndex = digitsToVerify.get(0);
                // add the number to the partial sum
                partialSum += partNumbersMatrix.get(i).get(digitIndex);
                // mark the current position as visited
                partNumbersMatrix.get(i).set(digitIndex, -2);
                // remove the current position from the list
                digitsToVerify.remove(0);

                // add the left and the right neighbor, if it is inside de matrix limits
                if (digitIndex - 1 >= 0) {
                    if (partNumbersMatrix.get(i).get(digitIndex - 1) >= 0) {
                        digitsToVerify.add(digitIndex - 1);
                    }
                }
                if (digitIndex + 1 < partNumbersMatrix.get(i).size()) {
                    if (partNumbersMatrix.get(i).get(digitIndex + 1) >= 0) {
                        digitsToVerify.add(digitIndex + 1);
                    }
                }
            }
            numbersToAdd.add(partialSum);
        }
    }
}


